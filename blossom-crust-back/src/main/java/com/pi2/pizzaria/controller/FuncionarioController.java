package com.pi2.pizzaria.controller;
import com.pi2.pizzaria.dto.FuncionarioDTO;
import com.pi2.pizzaria.exception.FuncionarioNotFoundException;
import com.pi2.pizzaria.model.Funcionario;
import com.pi2.pizzaria.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/funcionarios")
@CrossOrigin(origins = "http://localhost:4200")
public class FuncionarioController
{
        @Autowired
        private FuncionarioService funcionarioService;

        // Endpoint para salvar um Funcionario
        @PostMapping("/criar")
        public ResponseEntity<FuncionarioDTO> createFuncionario(@Valid @RequestBody FuncionarioDTO funcionarioDTO) {
            Funcionario funcionario = convertToEntity(funcionarioDTO);
            Funcionario savedFuncionario = funcionarioService.saveFuncionario(funcionario);
            return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(savedFuncionario));
        }

        // Endpoint para buscar todas os Funcionarios.
        @GetMapping("todas")
        public ResponseEntity<Map<String, List<FuncionarioDTO>>> getAllFuncionarios() {
            List<Funcionario> funcionarios = funcionarioService.getAllFuncionarios();
            List<FuncionarioDTO> funcionarioDTOs = funcionarios.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            Map<String, List<FuncionarioDTO>> funcionarioMap = new HashMap<>();
            funcionarioMap.put("funcionarios", funcionarioDTOs);
            return ResponseEntity.ok(funcionarioMap);
        }

        // Endpoint para buscar uma pizza por ID
        @GetMapping("/{id}")
        public ResponseEntity<FuncionarioDTO> getFuncionarioById(@PathVariable Long id) {
            try {
                Funcionario funcionario = funcionarioService.getFuncionarioById(id);
                return ResponseEntity.ok(convertToDTO(funcionario));
            } catch (FuncionarioNotFoundException ex) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        }

        // Endpoint para buscar um Funcionario por nome
        @GetMapping("/nome/{nome}")
        public ResponseEntity<FuncionarioDTO> getFuncionarioByNome(@PathVariable String nome) {
            try {
                Funcionario funcionario = funcionarioService.getFuncionarioByNome(nome);
                return ResponseEntity.ok(convertToDTO(funcionario));
            } catch (FuncionarioNotFoundException ex) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        }

        // Endpoint para deletar um Funcionario por ID
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteFuncionario(@PathVariable Long id) {
            try {
                funcionarioService.deleteFuncionario(id);
                return ResponseEntity.noContent().build();
            } catch (FuncionarioNotFoundException ex) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }

        // Métodos auxiliares para conversão

        private FuncionarioDTO convertToDTO(Funcionario  funcionario) {
            FuncionarioDTO  funcionarioDTO = new FuncionarioDTO();
            funcionarioDTO.setIdFuncionario( funcionario.getIdFuncionario());
            funcionarioDTO.setNome( funcionario.getNome());
            return  funcionarioDTO;
        }

        private Funcionario convertToEntity(FuncionarioDTO funcionarioDTO) {
            Funcionario funcionario = new Funcionario();
            funcionario.setNome(funcionarioDTO.getNome());
            return funcionario;
        }
    }
