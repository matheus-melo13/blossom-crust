import http from 'k6/http';
import { check, group, sleep, fail } from 'k6';

// Configuração geral do teste com incremento de VUs
export const options = {
    stages: [
        { duration: '10s', target: 1 },    // 1 usuário no início
        { duration: '10s', target: 10 },  // 10 usuários após 10 segundos
        { duration: '10s', target: 20 },  // 20 usuários após 20 segundos
        { duration: '10s', target: 30 },  // 30 usuários após 30 segundos
        { duration: '10s', target: 40 },  // 40 usuários após 40 segundos
        { duration: '10s', target: 50 },  // 50 usuários após 50 segundos
        { duration: '10s', target: 60 },  // 60 usuários após 1 minuto
        { duration: '10s', target: 70 },  // 70 usuários após 1min10s
        { duration: '10s', target: 80 },  // 80 usuários após 1min20s
        { duration: '10s', target: 90 },  // 90 usuários após 1min30s
        { duration: '10s', target: 100 }, // 100 usuários após 1min40s
    ],
};

// URL Base da API
const BASE_URL = 'http://localhost:8080/pizzas';

// Função para gerar dados aleatórios de pizza
function gerarPizzaAleatoria() {
    const sabores = [
        'Calabresa', 'Mussarela', 'Frango com Catupiry', 'Marguerita',
        'Pepperoni', 'Portuguesa', 'Quatro Queijos', 'Vegetariana',
        'Napolitana', 'Mexicana',
    ];

    const descricaoBase = 'Pizza deliciosa de';
    const precoMin = 20.0;
    const precoMax = 60.0;

    const sabor = sabores[Math.floor(Math.random() * sabores.length)];
    const preco = (Math.random() * (precoMax - precoMin) + precoMin).toFixed(2);

    return {
        sabor: sabor,
        preco: parseFloat(preco),
        descricao: `${descricaoBase} ${sabor.toLowerCase()}`,
    };
}

// Função principal que será executada pelos VUs
export default function () {
    group("Testes de CRUD para Pizzaria", () => {
        // ** 1. Criar pizza (POST) **
        const novaPizza = gerarPizzaAleatoria();
        const criarResposta = http.post(`${BASE_URL}/criar`, JSON.stringify(novaPizza), {
            headers: { 'Content-Type': 'application/json' },
        });

        check(criarResposta, {
            'POST /criar - status 201': (res) => res.status === 201,
            'POST /criar - ID retornado': (res) => res.json() && res.json().id !== undefined,
        });

        if (criarResposta.status !== 201) {
            fail(`Erro ao criar pizza. Resposta: ${criarResposta.body}`);
        }

        const criadaPizzaId = criarResposta.json().id;

        // ** 2. Buscar pizza pelo ID (GET) **
        const getByIdResposta = http.get(`${BASE_URL}/${criadaPizzaId}`);
        check(getByIdResposta, {
            'GET /{id} - status 200': (res) => res.status === 200,
            'GET /{id} - sabor correto': (res) => res.json() && res.json().sabor === novaPizza.sabor,
        });

        // ** 3. Atualizar pizza (PUT) **
        const pizzaAtualizada = {
            sabor: `${novaPizza.sabor} Especial`,
            preco: novaPizza.preco + 5,
            descricao: `${novaPizza.descricao} - Atualizada!`,
        };
        const putResposta = http.put(
            `${BASE_URL}/${criadaPizzaId}`,
            JSON.stringify(pizzaAtualizada),
            { headers: { 'Content-Type': 'application/json' } },
        );

        check(putResposta, {
            'PUT /{id} - status 200': (res) => res.status === 200,
            'PUT /{id} - sabor atualizado': (res) => res.json() && res.json().sabor === pizzaAtualizada.sabor,
        });

        // ** 4. Obter todas as pizzas (GET ALL) **
        const getAllResposta = http.get(`${BASE_URL}/todas`);
        check(getAllResposta, {
            'GET /todas - status 200': (res) => res.status === 200,
            'GET /todas - lista não vazia': (res) => res.json() && res.json().pizzas.length > 0,
        });

        // ** 5. Deletar a pizza criada (DELETE) **
        const deleteResposta = http.del(`${BASE_URL}/${criadaPizzaId}`);
        check(deleteResposta, {
            'DELETE /{id} - status 204 ou menor': (res) => res.status === 204 || res.status < 300,
        });

        // ** 6. Verificar se a pizza foi deletada (GET após DELETE) **
        const verificarDelete = http.get(`${BASE_URL}/${criadaPizzaId}`);
        check(verificarDelete, {
            'GET após DELETE - status 404': (res) => res.status === 404,
        });

        // Pausa entre grupos de requisições
        sleep(1);
    });

    group("Teste de erros controlados", () => {
        // ** 7. Criar pizza inválida (POST com Sabor faltando) **
        const invalidPayload = {
            preco: 30.5,
            descricao: "Pizza sem sabor",
        };
        const invalidPostResposta = http.post(`${BASE_URL}/criar`, JSON.stringify(invalidPayload), {
            headers: { 'Content-Type': 'application/json' },
        });

        check(invalidPostResposta, {
            'POST inválido - status 400 ou 422': (res) =>
                res.status === 400 || res.status === 422,
        });

        // ** 8. Buscar pizza inválida pelo ID inexistente **
        const invalidGetResposta = http.get(`${BASE_URL}/999999`);
        check(invalidGetResposta, {
            'GET ID inexistente - status 404': (res) => res.status === 404,
        });
    });
}