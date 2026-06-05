package com.matheus.gerenciadorDeAlunos.backend.shared.exceptions;

import java.util.UUID;

public class IdNotFoundException extends RuntimeException {
    public IdNotFoundException(UUID id) {
        super("Id: "+id+" não encontrado");
    }
}
