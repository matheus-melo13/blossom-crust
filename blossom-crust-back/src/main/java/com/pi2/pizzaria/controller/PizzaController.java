package com.pi2.pizzaria.controller;

import com.pi2.pizzaria.dto.PizzaDTO;
import com.pi2.pizzaria.exception.PizzaNotFoundException;
import com.pi2.pizzaria.model.Pizza;
import com.pi2.pizzaria.service.PizzaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pizzas")
@CrossOrigin(origins = "http://localhost:4200")
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;

    // Endpoint para salvar uma pizza
    @PostMapping("/criar")
    public ResponseEntity<PizzaDTO> createPizza(@Valid @RequestBody PizzaDTO pizzaDTO) {
        Pizza pizza = convertToEntity(pizzaDTO);
        Pizza savedPizza = pizzaService.savePizza(pizza);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(savedPizza));
    }

    // Endpoint para buscar todas as pizzas
    @GetMapping("todas")
    public ResponseEntity<Map<String, List<PizzaDTO>>> getAllPizzas() {
        List<Pizza> pizzas = pizzaService.getAllPizzas();
        List<PizzaDTO> pizzaDTOs = pizzas.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        Map<String, List<PizzaDTO>> pizzasMap = new HashMap<>();
        pizzasMap.put("pizzas", pizzaDTOs);
        return ResponseEntity.ok(pizzasMap);
    }

    // Endpoint para buscar uma pizza por ID
    @GetMapping("/{id}")
    public ResponseEntity<PizzaDTO> getPizzaById(@PathVariable Long id) {
        try {
            Pizza pizza = pizzaService.getPizzaById(id);
            return ResponseEntity.ok(convertToDTO(pizza));
        } catch (PizzaNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Endpoint para buscar uma pizza por sabor
    @GetMapping("/sabor/{sabor}")
    public ResponseEntity<PizzaDTO> getPizzaBySabor(@PathVariable String sabor) {
        try {
            Pizza pizza = pizzaService.getPizzaBySabor(sabor);
            return ResponseEntity.ok(convertToDTO(pizza));
        } catch (PizzaNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Endpoint para deletar uma pizza por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePizza(@PathVariable Long id) {
        try {
            pizzaService.deletePizza(id);
            return ResponseEntity.noContent().build();
        } catch (PizzaNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Métodos auxiliares para conversão

    private PizzaDTO convertToDTO(Pizza pizza) {
        PizzaDTO pizzaDTO = new PizzaDTO();
        pizzaDTO.setId(pizza.getId());
        pizzaDTO.setSabor(pizza.getSabor());
        pizzaDTO.setPreco(pizza.getPreco());
        pizzaDTO.setDescricao(pizza.getDescricao());
        return pizzaDTO;
    }

    private Pizza convertToEntity(PizzaDTO pizzaDTO) {
        Pizza pizza = new Pizza();
        pizza.setSabor(pizzaDTO.getSabor());
        pizza.setPreco(pizzaDTO.getPreco());
        pizza.setDescricao(pizzaDTO.getDescricao());
        return pizza;
    }
}
