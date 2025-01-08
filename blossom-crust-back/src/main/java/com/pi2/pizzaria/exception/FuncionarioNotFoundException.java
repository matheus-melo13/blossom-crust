package com.pi2.pizzaria.exception;

public class FuncionarioNotFoundException extends RuntimeException
{

    public FuncionarioNotFoundException(String message) {
        super(message);
    }
}
