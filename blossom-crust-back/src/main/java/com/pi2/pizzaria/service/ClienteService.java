package com.pi2.pizzaria.service;

import com.pi2.pizzaria.exception.ClienteNotFoundException;
import com.pi2.pizzaria.model.Cliente;
import com.pi2.pizzaria.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente saveCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    public Cliente getClienteById(Long id) {
        return clienteRepository.findById(id).orElseThrow(() -> new ClienteNotFoundException("Cliente não encontrado"));
    }

    public Cliente updateCliente(Long id, Cliente clienteDetails) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new ClienteNotFoundException("Cliente não encontrado"));
        cliente.setNome(clienteDetails.getNome());
        cliente.setEmail(clienteDetails.getEmail());
        cliente.setSenha(clienteDetails.getSenha());
        cliente.setEndereco(clienteDetails.getEndereco());
        cliente.setTelefone(clienteDetails.getTelefone());
        return clienteRepository.save(cliente);
    }

    public void deleteCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new ClienteNotFoundException("Cliente não encontrado"));
        clienteRepository.delete(cliente);
    }
}
