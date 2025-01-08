package com.pi2.pizzaria.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public class FuncionarioDTO 
{
    private Long idFuncionario;

    @NotBlank(message = "O Nome do Funcionario não pode ser vazio")
    @Size(max = 100, message = "O Nome do Funcionario deve ter no máximo 100 caracteres")
    private String nome;

    // Construtores
    public FuncionarioDTO() {
    }
    public FuncionarioDTO(Long idFuncionario, String nome) {
        this.idFuncionario = idFuncionario;
        this.nome = nome;
    }
    // Getters e Setters

    public Long getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Long idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    @Override
    public String toString() {
        return "FuncionarioDTO{" +
                "idFuncionario=" + idFuncionario +
                ", nome='" + nome + '\'' +
                '}';
    }
}
