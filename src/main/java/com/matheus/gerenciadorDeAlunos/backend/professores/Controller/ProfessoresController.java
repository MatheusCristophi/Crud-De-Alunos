package com.matheus.gerenciadorDeAlunos.backend.professores.Controller;

import com.matheus.gerenciadorDeAlunos.backend.professores.Controller.request.ProfessoresRequest;
import com.matheus.gerenciadorDeAlunos.backend.professores.Controller.response.ProfessoresResponse;
import com.matheus.gerenciadorDeAlunos.backend.professores.Service.ProfessorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController("/professor")
public class ProfessoresController {
    ProfessorService service;

    public ProfessoresController(ProfessorService service) {
        this.service = service;
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<List<ProfessoresResponse>> getAllUser(){
        var listProf = service.showAllTeachers();
        var response = listProf
                .stream()
                .map(ProfessoresResponse::toProfessores)
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/getuser/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<ProfessoresResponse> getUserById(@PathVariable UUID id){
        var profId = service.showTeacherById(id);
        var response = ProfessoresResponse.toProfessores(profId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<ProfessoresResponse> updateUser(@PathVariable UUID id, @RequestBody @Valid ProfessoresRequest professores){
        var professor = service.updateTeatcher(id, professores);
        var response = ProfessoresResponse.toProfessores(professor);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id){
        service.deleteTeacherById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
