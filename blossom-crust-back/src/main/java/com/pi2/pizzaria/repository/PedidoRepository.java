package com.pi2.pizzaria.repository;
import com.pi2.pizzaria.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    /**
     * Busca todos os pedidos associados a um cliente específico.
     *
     * @param idCliente O ID do cliente.
     * @return Uma lista de pedidos associados ao cliente.
     */
    List<Pedido> findByIdCliente(Long idCliente);
}