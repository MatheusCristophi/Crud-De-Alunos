package com.matheus.gerenciadorDeAlunos.backend.professores.Controller.response;

import com.matheus.gerenciadorDeAlunos.backend.alunos.model.Alunos;
import com.matheus.gerenciadorDeAlunos.backend.professores.model.Professores;

import java.util.Set;

public record ProfessoresResponse(String nome,
                                  int idade,
                                  Set<Alunos> alunos) {

    public static ProfessoresResponse toProfessores(Professores professores){
        return new ProfessoresResponse(
                professores.getName(),
                professores.getIdade(),
                professores.getAlunos()
        );
    }
}
