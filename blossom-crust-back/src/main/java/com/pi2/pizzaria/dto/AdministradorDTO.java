package com.pi2.pizzaria.dto;

public class AdministradorDTO {

    private Long idAdmin;
    private String nome;

    // Construtores
    public AdministradorDTO() {
    }

    public AdministradorDTO(Long idAdmin, String nome) {
        this.idAdmin = idAdmin;
        this.nome = nome;
    }

    // Getters e setters
    public Long getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(Long idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "AdministradorDTO{" +
                "idAdmin=" + idAdmin +
                ", nome='" + nome + '\'' +
                '}';
    }
}
