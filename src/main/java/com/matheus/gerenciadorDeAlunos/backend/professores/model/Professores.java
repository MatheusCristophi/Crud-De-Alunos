package com.matheus.gerenciadorDeAlunos.backend.professores.model;

import com.matheus.gerenciadorDeAlunos.backend.alunos.model.Alunos;
import com.matheus.gerenciadorDeAlunos.backend.shared.enums.RoleEnums;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Table(name = "tb_professores")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Professores implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
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
}
