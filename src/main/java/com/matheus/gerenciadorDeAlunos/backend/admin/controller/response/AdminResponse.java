package com.matheus.gerenciadorDeAlunos.backend.admin.controller.response;


import com.matheus.gerenciadorDeAlunos.backend.admin.model.Admin;

public record AdminResponse(String nome,
                            String email) {

    public static AdminResponse toAdmin(Admin admin){
        return new AdminResponse(
                admin.getNome(),
                admin.getEmail()
        );
    }
}
