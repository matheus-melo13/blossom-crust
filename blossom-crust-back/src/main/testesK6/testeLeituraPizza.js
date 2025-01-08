import http from 'k6/http';
import { sleep, check } from 'k6';
import { Trend, Rate, Counter } from 'k6/metrics';

// Métricas personalizadas
const latencia = new Trend('latencia_leitura'); // Tempo de resposta (latência)
const falhas = new Rate('taxa_falhas'); // Taxa de requisições que falham
const requisicoesTotais = new Counter('total_requisicoes'); // Total de requisições realizadas

// Configuração com número fixo de VUs
export const options = {
    //foram feitos com VUS de: 1, 25, 50, 100, 250, 500
    vus: 1000, // Número fixo de Virtual Users (fixo em 20, mas você pode alterar)
    duration: '30s', // Duração total do teste
    thresholds: {
        // SLAs: Definir critérios de sucesso para o teste
        http_req_duration: ['p(95)<300'], // 95% das requisições devem ser respondidas em menos de 300ms
        taxa_falhas: ['rate<0.01'],       // Menos de 1% das requisições podem falhar
    },
};

// URL Base e sabor da pizza a ser lida
const BASE_URL = 'http://localhost:8080/pizzas/sabor';
const SABOR = 'Marguerita'; // Pizza alvo para o teste

export default function () {
    // Realiza a requisição GET para o endpoint que busca a pizza por sabor
    const res = http.get(`${BASE_URL}/${SABOR}`);

    // Registrando métricas personalizadas
    latencia.add(res.timings.duration);            // Registrar latência da requisição
    falhas.add(res.status !== 200);                // Registrar falhas se o status não for 200
    requisicoesTotais.add(1);                      // Incrementar o total de requisições

    // Validações (checks) para respostas específicas
    check(res, {
        'status 200 OK': (r) => r.status === 200,                                      // Verifica se o status HTTP é 200
        'conteúdo correto': (r) => r.json().sabor === SABOR,                           // Verifica se o sabor é "Marguerita"
        'id está presente': (r) => r.json().id !== undefined,                          // Verifica se o ID existe
        'preço é maior que zero': (r) => r.json().preco !== undefined && r.json().preco > 0, // Garante que o preço é válido
    });

    sleep(1); // Pausa de 1 segundo entre cada requisição por VU
}

// Função para coletar e exibir métricas consolidadas ao final do teste
export function handleSummary(data) {
    const metrics = {
        Latencia_media: `${data.metrics.http_req_duration.avg.toFixed(2)} ms`,
        Latencia_95: `${data.metrics.http_req_duration['p(95)'].toFixed(2)} ms`,
        Taxa_de_Falhas: `${(data.metrics.http_req_failed.rate * 100).toFixed(2)}%`,
        Requisicoes_Totais: data.metrics.http_reqs.count,
    };

    console.log(`\n==================== Resumo de Resultados ====================`);
    console.log(`Latência Média: ${metrics.Latencia_media}`);
    console.log(`Latência 95%: ${metrics.Latencia_95}`);
    console.log(`Taxa de Falhas: ${metrics.Taxa_de_Falhas}`);
    console.log(`Requisições Totais Executadas: ${metrics.Requisicoes_Totais}`);
    console.log(`=============================================================\n`);

    return {
        stdout: JSON.stringify(metrics, null, 2), // Exporta métricas agregadas no console em formato JSON
    };

}