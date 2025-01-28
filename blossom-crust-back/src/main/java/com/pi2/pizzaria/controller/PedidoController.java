package com.pi2.pizzaria.controller;


import com.pi2.pizzaria.dto.PedidoDto;
import com.pi2.pizzaria.model.Pedido;
import com.pi2.pizzaria.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin(origins = "http://localhost:4200")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping("criar")
    public ResponseEntity<Pedido> createPedido(
            @RequestBody PedidoDto pedidoDto) {
        try {
            Pedido pedido = pedidoService.createPedido(pedidoDto.getIdCliente(), pedidoDto.getIdPizzas());
            return ResponseEntity.ok(pedido);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(null);
        }
    }

    /**
     * Busca todos os pedidos no sistema.
     *
     * @return Uma lista com todos os pedidos.
     */
    @GetMapping("todos")
    public ResponseEntity<List<Pedido>> getAllPedidos() {
        List<Pedido> pedidos = pedidoService.getAllPedidos();
        return ResponseEntity.ok(pedidos);
    }

    /**
     * Busca um pedido específico pelo seu ID.
     *
     * @param idPedido O ID do pedido.
     * @return O pedido encontrado ou uma mensagem de erro.
     */
    @GetMapping("/{idPedido}")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable Long idPedido) {
        try {
            Pedido pedido = pedidoService.getPedidoById(idPedido);
            return ResponseEntity.ok(pedido);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<Pedido>> getPedidoByCliente(@PathVariable Long idCliente) {
        try {
            List<Pedido> pedidos = pedidoService.getPedidosByCliente(idCliente);
            return ResponseEntity.ok(pedidos);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Método para atualizar o status do pedido
    @PutMapping("/{idPedido}/status/{novoStatus}")
    public ResponseEntity<Pedido> updatePedidoStatus(
            @PathVariable Long idPedido,
            @PathVariable String novoStatus) {
        try {
            Pedido pedidoAtualizado = pedidoService.updateStatus(idPedido, novoStatus);
            return ResponseEntity.ok(pedidoAtualizado);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
