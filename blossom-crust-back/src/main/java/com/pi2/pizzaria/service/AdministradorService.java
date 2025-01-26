package com.pi2.pizzaria.service;

import com.pi2.pizzaria.dto.AdministradorDTO;
import com.pi2.pizzaria.model.Administrador;
import com.pi2.pizzaria.repository.AdministradorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdministradorService {

    private final AdministradorRepository administradorRepository;

    public AdministradorService(AdministradorRepository administradorRepository) {
        this.administradorRepository = administradorRepository;
    }

    // Buscar todos os administradores
    public List<AdministradorDTO> findAll() {
        return administradorRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Buscar administrador por ID
    public Optional<AdministradorDTO> findById(Long idAdmin) {
        return administradorRepository.findById(idAdmin).map(this::toDTO);
    }

    // Salvar administrador
    public AdministradorDTO save(AdministradorDTO dto) {
        Administrador administrador = toEntity(dto);
        administrador = administradorRepository.save(administrador);
        return toDTO(administrador);
    }

    // Atualizar administrador
    public Optional<AdministradorDTO> update(Long idAdmin, AdministradorDTO dto) {
        return administradorRepository.findById(idAdmin).map(existing -> {
            existing.setNome(dto.getNome());
            return toDTO(administradorRepository.save(existing));
        });
    }

    // Deletar administrador por ID
    public void deleteById(Long idAdmin) {
        administradorRepository.deleteById(idAdmin);
    }

    // Métodos de conversão
    private AdministradorDTO toDTO(Administrador administrador) {
        return new AdministradorDTO(administrador.getIdAdmin(), administrador.getNome());
    }

    private Administrador toEntity(AdministradorDTO dto) {
        return new Administrador(dto.getNome());
    }
}
