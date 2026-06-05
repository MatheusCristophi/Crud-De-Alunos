package com.matheus.gerenciadorDeAlunos.backend.alunos.controller.response;

import com.matheus.gerenciadorDeAlunos.backend.alunos.model.Alunos;
import com.matheus.gerenciadorDeAlunos.backend.professores.model.Professores;

import java.util.List;

public record AlunosResponse(String nome,
                             int periodo,
                             List<Float> notasT,
                             List<Professores> professores) {

    public static AlunosResponse toAluno(Alunos alunos){
        return new AlunosResponse(
                alunos.getNome(),
                alunos.getPeriodo(),
                alunos.getNotasT(),
                alunos.getProfessores()
        );
    }
}
