package com.pi2.pizzaria.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "colaboradores")
public class Colaborador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long idColaborador;

    @Column( nullable = false, unique = true)
    private String email;

    @Column(nullable = false, length = 10)
    private String senha;

    @Column( nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 30)
    private String tipoColaborador;

    // Construtores, getters e setters
    public Colaborador() {
    }

    public Colaborador(String email, String senha, String nome, String tipoColaborador) {
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.tipoColaborador = tipoColaborador;
    }

    public Long getIdColaborador() {
        return idColaborador;
    }

    public void setIdColaborador(Long idColaborador) {
        this.idColaborador = idColaborador;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipoColaborador() {
        return tipoColaborador;
    }

    public void setTipoColaborador(String tipoColaborador) {
        this.tipoColaborador = tipoColaborador;
    }

    // Métodos auxiliares

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Colaborador that = (Colaborador) o;
        return Objects.equals(idColaborador, that.idColaborador);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idColaborador);
    }

    @Override
    public String toString() {
        return "Colaborador{" +
                "idColaborador=" + idColaborador +
                ", email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", tipoColaborador='" + tipoColaborador + '\'' +
                '}';
    }
}
