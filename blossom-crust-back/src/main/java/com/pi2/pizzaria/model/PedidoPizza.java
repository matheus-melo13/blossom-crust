package com.pi2.pizzaria.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class PedidoPizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID gerado automaticamente pelo PostgreSQL

    private Long idPedido;  // Referência ao pedido
    private Long idPizza;   // Referência à pizza

    // Construtores
    public PedidoPizza() {}

    public PedidoPizza(Long idPedido, Long idPizza) {
        this.idPedido = idPedido;
        this.idPizza = idPizza;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public Long getIdPizza() {
        return idPizza;
    }

    public void setIdPizza(Long idPizza) {
        this.idPizza = idPizza;
    }
}
