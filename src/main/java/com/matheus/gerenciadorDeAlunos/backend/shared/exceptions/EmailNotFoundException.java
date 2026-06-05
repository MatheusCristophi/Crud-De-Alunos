package com.matheus.gerenciadorDeAlunos.backend.shared.exceptions;

public class EmailNotFoundException extends RuntimeException {
    public EmailNotFoundException(String email) {
        super("Email: "+ email+ "não encontrado");
    }
}
