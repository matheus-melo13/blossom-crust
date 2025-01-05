package com.pi2.pizzaria.repository;

import com.pi2.pizzaria.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>
{
    Funcionario findByNome(String nome);
}
