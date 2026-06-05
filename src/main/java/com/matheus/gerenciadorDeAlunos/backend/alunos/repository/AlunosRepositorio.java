package com.matheus.gerenciadorDeAlunos.backend.alunos.repository;

import com.matheus.gerenciadorDeAlunos.backend.alunos.model.Alunos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AlunosRepositorio extends JpaRepository<Alunos, UUID> {

    Optional<Alunos> findByName(String name);

    Optional<UserDetails> findByEmail(String email);
}
