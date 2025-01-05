package com.pi2.pizzaria.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "administradores")
public class Administrador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long idAdmin;

    @Column(nullable = false, length = 100)
    private String nome;

    // Construtores, getters e setters
    public Administrador() {
    }

    public Administrador(String nome) {
        this.nome = nome;
    }

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

    // Métodos auxiliares

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Administrador that = (Administrador) o;
        return Objects.equals(idAdmin, that.idAdmin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAdmin);
    }

    @Override
    public String toString() {
        return "Administrador{" +
                "idAdmin=" + idAdmin +
                ", nome='" + nome + '\'' +
                '}';
    }
}

