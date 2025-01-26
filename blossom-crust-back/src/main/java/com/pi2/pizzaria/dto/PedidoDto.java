package com.pi2.pizzaria.dto;

import java.util.List;

public class PedidoDto {
    private Long idCliente;
    private List<Long> idPizzas;

    // Getters e Setters
    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public List<Long> getIdPizzas() {
        return idPizzas;
    }

    public void setIdPizzas(List<Long> idPizzas) {
        this.idPizzas = idPizzas;
    }
}
