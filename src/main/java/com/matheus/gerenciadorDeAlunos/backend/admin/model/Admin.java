package com.matheus.gerenciadorDeAlunos.backend.admin.model;

import com.matheus.gerenciadorDeAlunos.backend.shared.enums.RoleEnums;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_administradores")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Admin implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String nome;

    @Column(name = "email", unique = true , nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String senha;

    @Column(name = "role", nullable = false)
    public RoleEnums role;

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
