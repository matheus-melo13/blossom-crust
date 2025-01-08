import http from 'k6/http';
import { check } from 'k6';

// Configurações para o teste
export const options = {
    stages: [
	//foram feitos com VUS de: 1, 25, 50, 100, 250, 500
        { duration: '30s', target: 500 }, // 1 usuário por 30 segundos
    ],
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
