package com.pi2.pizzaria.service;

import com.pi2.pizzaria.exception.FuncionarioNotFoundException;
import com.pi2.pizzaria.model.Funcionario;
import com.pi2.pizzaria.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public Funcionario saveFuncionario(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    // Metodo para buscar todos os funcionarios
    public List<Funcionario> getAllFuncionarios() {
        return funcionarioRepository.findAll();
    }

    // Metodo para buscar um funcionario por ID
    public Funcionario getFuncionarioById(Long idFuncionario) {
        Optional<Funcionario> funcionario = funcionarioRepository.findById(idFuncionario);
        if (funcionario.isEmpty()) {
            throw new FuncionarioNotFoundException("Funcionario não encontrado com ID: " + idFuncionario);
        }
        return funcionario.get();
    }

    public Funcionario getFuncionarioByNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do funcionário não pode ser nulo ou vazio.");
        }
        Funcionario funcionario = funcionarioRepository.findByNome(nome);
        if (funcionario == null) {
            throw new FuncionarioNotFoundException("Funcionario não encontrado com nome: " + nome);
        }
        return funcionario;
    }
    
    // Metodo para deletar um funcionario por ID
    public void deleteFuncionario(Long idFuncionario) {
        if (!funcionarioRepository.existsById(idFuncionario)) {
            throw new FuncionarioNotFoundException("Funcionario não encontrado com ID: " + idFuncionario);
        }
        funcionarioRepository.deleteById(idFuncionario);
    }
    }

