package com.matheus.gerenciadorDeAlunos.backend.admin.service;

import com.matheus.gerenciadorDeAlunos.backend.admin.controller.request.AdminRequest;
import com.matheus.gerenciadorDeAlunos.backend.admin.model.Admin;
import com.matheus.gerenciadorDeAlunos.backend.admin.repository.AdminRepository;
import com.matheus.gerenciadorDeAlunos.backend.shared.enums.RoleEnums;
import com.matheus.gerenciadorDeAlunos.backend.shared.exceptions.EmailNotFoundException;
import com.matheus.gerenciadorDeAlunos.backend.shared.exceptions.IdNotFoundException;
import com.matheus.gerenciadorDeAlunos.backend.shared.exceptions.adminExceptions.AdminException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class AdminService {

    private final AdminRepository repository;
    private final PasswordEncoder encoder;

    public AdminService(AdminRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Transactional
    public Admin registrar(AdminRequest admin) {
        try {
            Admin adminCreate = new Admin();

            adminCreate.setNome(admin.nome());
            adminCreate.setEmail(admin.email());
            adminCreate.setSenha(encoder.encode(admin.senha()));
            adminCreate.setRole(RoleEnums.ADMINISTRADOR);
            return repository.save(adminCreate);

        }catch(AdminException exception){
            throw new AdminException("Erro ao cadastrar um Administrador");
        }
    }

    public Admin buscarEmail(String email){
        return repository.findByEmail(email)
                .orElseThrow(() -> new EmailNotFoundException(email));
    }

    @Transactional
    public void deleteById(UUID id) {
        if (id != null) {
            repository.deleteById(id);
        } else {
            throw new IdNotFoundException(id);
        }
    }
}
