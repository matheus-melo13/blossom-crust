package com.pi2.pizzaria.repository;

import com.pi2.pizzaria.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Long> {
    // JpaRepository já fornece métodos padrão, mas você pode criar customizados, como:
    Pizza findBySabor(String sabor); // Exemplo de consulta customizada
}
