import http from 'k6/http';
import { check } from 'k6';

// Configurações para o teste
export const options = {
    //foram feitos com VUS de: 1, 25, 50, 100, 250, 500
    vus: 1000, // Número fixo de Virtual Users (fixo em 20, mas você pode alterar)
    duration: '30s', // Duração total do teste
    thresholds: {
        // SLAs: Definir critérios de sucesso para o teste
        http_req_duration: ['p(95)<300'], // 95% das requisições devem ser respondidas em menos de 300ms
    },
};

export default function () {
    // Dados diretamente no código (sabor, preco, descricao)
    const payload = JSON.stringify({
        sabor: 'Calabresa',
        preco: 29.99,
        descricao: 'Pizza de calabresa com queijo',
    });

    // Realiza a requisição POST para o endpoint de criação de pizza no localhost:8080
    const res = http.post('http://localhost:8080/pizzas/criar', payload, {
        headers: { 'Content-Type': 'application/json' }, // Define o tipo de conteúdo como JSON
    });

    // Verifica se a resposta tem status 200
    check(res, {
        'status é 201': (r) => r.status === 201,
    });
}
