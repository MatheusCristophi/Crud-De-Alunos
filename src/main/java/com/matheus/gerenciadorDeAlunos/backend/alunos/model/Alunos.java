package com.matheus.gerenciadorDeAlunos.backend.alunos.model;

import com.matheus.gerenciadorDeAlunos.backend.shared.enums.RoleEnums;
import com.matheus.gerenciadorDeAlunos.backend.professores.model.Professores;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Table(name = "tb_alunos")

public class Alunos implements UserDetails {

    @Id
    @Column(nullable = false)
    private UUID alunoId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    public RoleEnums role;

    @Column(nullable = false)
    private int periodo;

    @ElementCollection
    @CollectionTable(name = "tb_notas_alunos", joinColumns = @JoinColumn(name = "aluno_id"))
    @Column(name = "nota")
    private List<Float> notasT;

    @ManyToMany(mappedBy = "alunos")
    private List<Professores> professores;

    public Alunos() {
    }

    public Alunos(UUID alunoId, String email, String senha, String nome, RoleEnums role, int periodo) {
        this.alunoId = alunoId;
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.role = role;
        this.periodo = periodo;
    }

    public UUID getAlunoId() {
        return alunoId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Float> getNotasT() {
        return notasT;
    }

    public void setNotasT(List<Float> notasT) {
        this.notasT = notasT;
    }

    public List<Professores> getProfessores() {
        return professores;
    }

    public void setProfessores(List<Professores> professores) {
        this.professores = professores;
    }

    public RoleEnums getRole() {
        return role;
    }

    public void setRole(RoleEnums role) { this.role = role; }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.getRoleEnums()));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}