package com.pi2.pizzaria.controller;

import com.pi2.pizzaria.dto.AdministradorDTO;
import com.pi2.pizzaria.service.AdministradorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/administradores")
public class AdministradorController {

    private final AdministradorService administradorService;

    public AdministradorController(AdministradorService administradorService) {
        this.administradorService = administradorService;
    }

    // Buscar todos os administradores
    @GetMapping
    public ResponseEntity<List<AdministradorDTO>> getAllAdministradores() {
        List<AdministradorDTO> administradores = administradorService.findAll();
        return ResponseEntity.ok(administradores);
    }

    // Buscar administrador por ID
    @GetMapping("/{id}")
    public ResponseEntity<AdministradorDTO> getAdministradorById(@PathVariable Long id) {
        return administradorService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Criar novo administrador
    @PostMapping
    public ResponseEntity<AdministradorDTO> createAdministrador(@RequestBody AdministradorDTO administradorDTO) {
        AdministradorDTO savedAdministrador = administradorService.save(administradorDTO);
        return ResponseEntity.status(201).body(savedAdministrador);
    }

    // Atualizar administrador por ID
    @PutMapping("/{id}")
    public ResponseEntity<AdministradorDTO> updateAdministrador(
            @PathVariable Long id,
            @RequestBody AdministradorDTO administradorDTO) {
        return administradorService.update(id, administradorDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Deletar administrador por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdministrador(@PathVariable Long id) {
        administradorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
