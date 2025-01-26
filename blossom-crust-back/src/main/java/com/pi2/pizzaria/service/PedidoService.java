package com.pi2.pizzaria.service;

import com.pi2.pizzaria.model.Pedido;
import com.pi2.pizzaria.model.PedidoPizza;
import com.pi2.pizzaria.repository.PedidoPizzaRepository;
import com.pi2.pizzaria.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoPizzaRepository pedidoPizzaRepository;

    @Autowired
    private PizzaService pizzaService; // Supondo que existe um serviço para buscar valores das pizzas

    public Pedido createPedido(Long idCliente, List<Long> idPizzas) {
        // Calcular o total
        float total = 0;
        for (Long idPizza : idPizzas) {
            total += pizzaService.getPizzaPriceById(idPizza);
        }

        // Criar o pedido
        Pedido pedido = new Pedido();
//        pedido.setIdPedido(java.util.UUID.randomUUID().toString());
        pedido.setData(new Date());
        pedido.setStatus("Pendente");
        pedido.setTotal(total);
        pedido.setIdCliente(idCliente);

        pedido = pedidoRepository.save(pedido);

        // Criar registros na tabela PedidoPizza
        for (Long idPizza : idPizzas) {
            PedidoPizza pedidoPizza = new PedidoPizza(pedido.getIdPedido(), idPizza);
            pedidoPizzaRepository.save(pedidoPizza);
        }

        return pedido;
    }

    public List<Pedido> getAllPedidos() {
        return pedidoRepository.findAll();
    }

    public Pedido getPedidoById(Long idPedido) {
        return pedidoRepository.findById(idPedido)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado!"));
    }
    /**
     * Busca todos os pedidos de um cliente pelo ID do cliente.
     *
     * @param idCliente O ID do cliente.
     * @return Uma lista de pedidos do cliente.
     */
    public List<Pedido> getPedidosByCliente(Long idCliente) {
        return pedidoRepository.findByIdCliente(idCliente);
    }

    public Pedido updateStatus(Long idPedido, String novoStatus) {
        Pedido pedido = pedidoRepository.findById(idPedido)
                .orElseThrow(() -> new IllegalArgumentException("Pedido com ID " + idPedido + " não encontrado."));

        pedido.setStatus(novoStatus);
        return pedidoRepository.save(pedido);
    }

}