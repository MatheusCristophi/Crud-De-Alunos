package com.matheus.gerenciadorDeAlunos.backend.alunos.service;

import com.matheus.gerenciadorDeAlunos.backend.alunos.controller.request.AlunosRequest;
import com.matheus.gerenciadorDeAlunos.backend.alunos.model.Alunos;
import com.matheus.gerenciadorDeAlunos.backend.alunos.repository.AlunosRepositorio;
import com.matheus.gerenciadorDeAlunos.backend.shared.enums.RoleEnums;
import com.matheus.gerenciadorDeAlunos.backend.shared.exceptions.IdNotFoundException;
import com.matheus.gerenciadorDeAlunos.backend.shared.exceptions.alunoExceptions.AlunoException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class AlunosService{
    private final AlunosRepositorio repositorio;
    private final PasswordEncoder encoder;

    

    public AlunosService(AlunosRepositorio repositorio, PasswordEncoder encoder) {
        this.repositorio = repositorio;
        this.encoder = encoder;
    }


    @Transactional
    public Alunos registrarAluno(AlunosRequest request){
        try {

            Alunos alunos = new Alunos();
            alunos.setNome(request.nome());
            alunos.setEmail(request.email());
            alunos.setSenha(encoder.encode(request.senha()));
            alunos.setPeriodo(request.periodo());
            alunos.setRole(RoleEnums.ALUNO);

            return repositorio.save(alunos);
        }catch (AlunoException exception){
            throw new AlunoException("Não foi possível registrar o aluno");
        }
    }


    @Transactional
    public void deletarAlunoViaId(UUID id){
        if (id != null){
        repositorio.deleteById(id);
        } else {
            throw new IdNotFoundException(id);
        }
    }


    public List<Alunos> mostrarTodosAlunos(){
        return repositorio.findAll();
    }


    public Alunos mostrarAlunoViaId(UUID id){
        return repositorio.findById(id)
                .orElseThrow(()-> new IdNotFoundException(id));
    }

    public Alunos atualizarAluno(AlunosRequest request, UUID id){
            Alunos aluno = repositorio.findById(id)
                    .orElseThrow(() -> new IdNotFoundException(id));
            try {
                if (request.nome() != null) {
                    aluno.setNome(request.nome());
                }
                if (request.periodo() != 0) {
                    aluno.setPeriodo(request.periodo());
                }
                if (request.senha() != null) {
                    aluno.setSenha(encoder.encode(request.senha()));
                }
                return repositorio.save(aluno);
            } catch (AlunoException exception){
                throw new AlunoException("Erro ao atualizar os dados do Aluno");
            }
    }
}
