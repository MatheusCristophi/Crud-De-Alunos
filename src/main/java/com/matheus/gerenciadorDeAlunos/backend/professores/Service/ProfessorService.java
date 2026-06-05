package com.matheus.gerenciadorDeAlunos.backend.professores.Service;

import com.matheus.gerenciadorDeAlunos.backend.professores.Controller.request.ProfessoresRequest;
import com.matheus.gerenciadorDeAlunos.backend.professores.model.Professores;
import com.matheus.gerenciadorDeAlunos.backend.professores.Repository.ProfessoresRepositorio;
import com.matheus.gerenciadorDeAlunos.backend.shared.enums.RoleEnums;
import com.matheus.gerenciadorDeAlunos.backend.shared.exceptions.IdNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ProfessorService{
    private final ProfessoresRepositorio repositorio;
    private final PasswordEncoder encoder;

    public ProfessorService(ProfessoresRepositorio repositorio, PasswordEncoder encoder) {
        this.repositorio = repositorio;
        this.encoder = encoder;
    }

    @Transactional
    public Professores registrar(ProfessoresRequest request){
        if (repositorio.findByEmail(request.email()).isPresent()){
            throw new RuntimeException("Email ja cadastrado");
        }
        Professores professor = new Professores();
        professor.setName(request.nome());
        professor.setIdade(request.idade());
        professor.setEmail(request.email());
        professor.setSenha(encoder.encode(request.senha()));
        professor.setRole(RoleEnums.PROFESSOR);
        return repositorio.save(professor);
    }

    public List<Professores> showAllTeachers(){
        return repositorio.findAll();
    }

    public Professores showTeacherById(UUID id){
        return repositorio.findById(id)
                .orElseThrow(()-> new IdNotFoundException(id));
    }

    @Transactional
    public void deleteTeacherById(UUID professorId){
        try{
        repositorio.deleteById(professorId);
        } catch (IdNotFoundException e){
            throw new IdNotFoundException(professorId);
        }
    }

    @Transactional
    public Professores updateTeatcher(UUID id, ProfessoresRequest prof){
        Professores profAtt = repositorio.findById(id)
                .orElseThrow(()-> new IdNotFoundException(id));
        if (prof.nome() != null) {
            profAtt.setName(prof.nome());
        }
        if(prof.email() != null){
            profAtt.setEmail(prof.email());
        }
        if(prof.senha() != null){
            profAtt.setSenha(encoder.encode(prof.senha()));
        }
        if (prof.idade() != 0) {
            profAtt.setIdade(prof.idade());
        }
        return repositorio.save(profAtt);
    }
}
