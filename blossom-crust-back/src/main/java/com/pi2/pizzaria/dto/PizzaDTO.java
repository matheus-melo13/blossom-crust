package com.pi2.pizzaria.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class PizzaDTO {

    private Long id;

    @NotBlank(message = "O sabor da pizza não pode ser vazio")
    @NotNull(message = "O sabor da pizza é obrigatório")
    @Size(max = 30, message = "O sabor da pizza deve ter no máximo 30 caracteres")
    private String sabor;

    @NotNull(message = "O preço da pizza é obrigatório")
    @Positive(message = "O preço deve ser um valor positivo")
    private Double preco;

    @NotBlank(message = "A descrição da pizza não pode ser vazia")
    @Size(max = 300, message = "A descrição deve ter no máximo 300 caracteres")
    private String descricao;

    // Construtores
    public PizzaDTO() {
    }

    public PizzaDTO(Long id, String sabor, Double preco, String descricao) {
        this.id = id;
        this.sabor = sabor;
        this.preco = preco;
        this.descricao = descricao;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSabor() {
        return sabor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "PizzaDTO{" +
                "id=" + id +
                ", sabor='" + sabor + '\'' +
                ", preco=" + preco +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
