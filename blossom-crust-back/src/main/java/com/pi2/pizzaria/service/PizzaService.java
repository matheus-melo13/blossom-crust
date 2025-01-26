package com.pi2.pizzaria.service;

import com.pi2.pizzaria.exception.PizzaNotFoundException;
import com.pi2.pizzaria.model.Pizza;
import com.pi2.pizzaria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;

    // Metodo para salvar uma pizza
    public Pizza savePizza(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    // Metodo para buscar todas as pizzas
    public List<Pizza> getAllPizzas() {
        return pizzaRepository.findAll();
    }

    // Metodo para buscar uma pizza por ID
    public Pizza getPizzaById(Long id) {
        Optional<Pizza> pizza = pizzaRepository.findById(id);
        if (pizza.isEmpty()) {
            throw new PizzaNotFoundException("Pizza não encontrada com ID: " + id);
        }
        return pizza.get();
    }

    // Metodo para buscar uma pizza por sabor
    public Pizza getPizzaBySabor(String sabor) {
        Pizza pizza = pizzaRepository.findBySabor(sabor);
        if (pizza == null) {
            throw new PizzaNotFoundException("Pizza não encontrada com sabor: " + sabor);
        }
        return pizza;
    }

    // Metodo para deletar uma pizza por ID
    public void deletePizza(Long id) {
        if (!pizzaRepository.existsById(id)) {
            throw new PizzaNotFoundException("Pizza não encontrada com ID: " + id);
        }
        pizzaRepository.deleteById(id);
    }

    public Double getPizzaPriceById(Long idPizza) {
        Pizza pizza = pizzaRepository.findById(idPizza)
                .orElseThrow(() -> new IllegalArgumentException("Pizza com ID " + idPizza + " não encontrada."));
        return pizza.getPreco();
    }
}
