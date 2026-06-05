package com.matheus.gerenciadorDeAlunos.backend.professores.Controller.request;

import jakarta.validation.constraints.*;

public record ProfessoresRequest(@NotBlank
                                 String nome,

                                 @Email
                                 String email,

                                 @Size(min = 8, max = 20, message = "A senha deve conter de 8 a 20 caracteres")
                                 String senha,

                                 @NotNull
                                 int idade) {
}
