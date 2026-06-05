package com.matheus.gerenciadorDeAlunos.backend.professores.model;

import com.matheus.gerenciadorDeAlunos.backend.alunos.model.Alunos;
import com.matheus.gerenciadorDeAlunos.backend.shared.enums.RoleEnums;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Table(name = "tb_professores")
public class Professores implements UserDetails {

    @Id
    @Column(nullable = false)
    private UUID professorId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int idade;

    @Column(nullable = false)
    public RoleEnums role;

    public Professores() {
    }

    public Professores(Set<Alunos> alunos, RoleEnums role, int idade, String name, String senha, String email, UUID professorId) {
        this.alunos = alunos;
        this.role = role;
        this.idade = idade;
        this.name = name;
        this.senha = senha;
        this.email = email;
        this.professorId = professorId;
    }

    public UUID getProfessorId() {
        return professorId;
    }

    public void setProfessorId(UUID professorId) {
        this.professorId = professorId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public RoleEnums getRole() {
        return role;
    }

    public void setRole(RoleEnums role) { this.role = role; }

    public Set<Alunos> getAlunos() {
        return alunos;
    }

    public void setAlunos(Set<Alunos> alunos) {
        this.alunos = alunos;
    }

    @ManyToMany
    @JoinTable(
            name = "alunos_professores",
            joinColumns = @JoinColumn(name = "professor_id"),
            inverseJoinColumns = @JoinColumn(name = "aluno_id"))
    private Set<Alunos> alunos;

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
