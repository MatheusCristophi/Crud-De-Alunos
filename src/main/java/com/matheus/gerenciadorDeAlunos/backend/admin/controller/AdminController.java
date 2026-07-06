package com.matheus.gerenciadorDeAlunos.backend.admin.controller;

import com.matheus.gerenciadorDeAlunos.backend.admin.controller.response.AdminResponse;
import com.matheus.gerenciadorDeAlunos.backend.admin.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private AdminService service;

    public AdminController(AdminService service) {
        this.service = service;
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id){
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/findemail")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<AdminResponse> findByEmail(@RequestBody String email){
        var emailFind = service.buscarEmail(email);
        var response = AdminResponse.toAdmin(emailFind);
        return ResponseEntity.ok(response);
    }
}
