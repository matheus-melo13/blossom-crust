package com.pi2.pizzaria.repository;

import com.pi2.pizzaria.model.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
    // Métodos personalizados podem ser adicionados aqui, se necessário
}
