package com.pi2.pizzaria.service;

import com.pi2.pizzaria.model.PedidoPizza;
import com.pi2.pizzaria.repository.PedidoPizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoPizzaService {

    @Autowired
    private PedidoPizzaRepository pedidoPizzaRepository;

    /**
     * Salva um registro de PedidoPizza no banco de dados.
     *
     * @param pedidoPizza O objeto PedidoPizza a ser salvo.
     * @return O objeto PedidoPizza salvo.
     */
    public PedidoPizza savePedidoPizza(PedidoPizza pedidoPizza) {
        return pedidoPizzaRepository.save(pedidoPizza);
    }

    /**
     * Busca todos os registros de PedidoPizza.
     *
     * @return Uma lista contendo todos os registros de PedidoPizza.
     */
    public List<PedidoPizza> getAllPedidoPizzas() {
        return pedidoPizzaRepository.findAll();
    }

    /**
     * Busca os registros de PedidoPizza pelo ID do Pedido.
     *
     * @param idPedido O ID do Pedido.
     * @return Uma lista de PedidoPizza associados ao ID do Pedido.
     */
    public List<PedidoPizza> getPedidoPizzasByIdPedido(Long idPedido) {
        return pedidoPizzaRepository.findByIdPedido(idPedido);
    }

    /**
     * Exclui um registro de PedidoPizza pelo ID.
     *
     * @param id O ID do PedidoPizza a ser excluído.
     */
    public void deletePedidoPizzaById(Long id) {
        pedidoPizzaRepository.deleteById(id);
    }
}