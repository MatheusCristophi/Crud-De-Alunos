package com.matheus.gerenciadorDeAlunos.backend.alunos.controller;

import com.matheus.gerenciadorDeAlunos.backend.alunos.controller.request.AlunosRequest;
import com.matheus.gerenciadorDeAlunos.backend.alunos.controller.response.AlunosResponse;
import com.matheus.gerenciadorDeAlunos.backend.alunos.service.AlunosService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/aluno")
public class AlunosController {
    AlunosService service;

    public AlunosController(AlunosService service) {
        this.service = service;
    }

    @GetMapping("/read")
    @PreAuthorize("hasRole('ADMINISTRADOR', 'PROFESSOR')")
    public ResponseEntity<List<AlunosResponse>> mostrarTodosOsAlunos(){
        var listAlunos = service.mostrarTodosAlunos();
        var response = listAlunos
                .stream()
                .map(AlunosResponse::toAluno)
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/readId/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR', 'PROFESSOR')")
    public ResponseEntity<AlunosResponse> mostrarAlunoPeloId(@PathVariable UUID id){
        var aluno = service.mostrarAlunoViaId(id);
        var response = AlunosResponse.toAluno(aluno);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/updateId/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR', 'PROFESSOR')")
    public ResponseEntity<AlunosResponse> alunoAtualizado(@RequestBody @Valid AlunosRequest alunos,@PathVariable UUID id){
        var alunoUpdate = service.atualizarAluno(alunos, id);
        var response = AlunosResponse.toAluno(alunoUpdate);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR', 'PROFESSOR')")
    public ResponseEntity<Void> deletaraluno(@PathVariable UUID id){
        service.deletarAlunoViaId(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
