package com.pi2.pizzaria.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ClienteDTO {

    private Long id;

    @NotBlank(message = "O nome do cliente não pode ser vazio")
    @Size(max = 100, message = "O nome do cliente deve ter no máximo 100 caracteres")
    private String nome;

    @NotBlank(message = "O email do cliente não pode ser vazio")
    @Email(message = "Email inválido")
    @Size(max = 150, message = "O email deve ter no máximo 150 caracteres")
    private String email;

    @NotBlank(message = "A senha do cliente não pode ser vazia")
    private String senha;

    @NotBlank(message = "O endereço do cliente não pode ser vazio")
    @Size(max = 255, message = "O endereço deve ter no máximo 255 caracteres")
    private String endereco;

    @NotBlank(message = "O telefone do cliente não pode ser vazio")
    @Size(min = 10, max = 15, message = "O telefone deve ter entre 10 e 15 caracteres")
    private String telefone;
}
