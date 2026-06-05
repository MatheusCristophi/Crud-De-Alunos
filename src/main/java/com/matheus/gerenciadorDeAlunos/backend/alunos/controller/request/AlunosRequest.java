package com.matheus.gerenciadorDeAlunos.backend.alunos.controller.request;

import jakarta.validation.constraints.*;

public record AlunosRequest(@NotBlank
                            String nome,

                            @NotNull
                            int periodo,

                            @Email
                            String email,

                            @Size(min = 8, max = 20, message = "A senha deve conter de 8 a 20 caracteres")
                            String senha) {
}
