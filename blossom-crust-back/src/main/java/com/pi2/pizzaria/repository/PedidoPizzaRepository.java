package com.pi2.pizzaria.repository;

import com.pi2.pizzaria.model.PedidoPizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoPizzaRepository extends JpaRepository<PedidoPizza, Long> {

    /**
     * Busca todos os registros de PedidoPizza associados a um determinado ID de Pedido.
     *
     * @param idPedido O ID do Pedido.
     * @return Uma lista de PedidoPizza associados ao ID do Pedido.
     */
    List<PedidoPizza> findByIdPedido(Long idPedido);
}