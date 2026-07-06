package com.matheus.gerenciadorDeAlunos.backend.alunos.model;

import com.matheus.gerenciadorDeAlunos.backend.shared.enums.RoleEnums;
import com.matheus.gerenciadorDeAlunos.backend.professores.model.Professores;
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
@Table(name = "tb_alunos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Alunos implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
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
    private Set<Professores> professores;


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