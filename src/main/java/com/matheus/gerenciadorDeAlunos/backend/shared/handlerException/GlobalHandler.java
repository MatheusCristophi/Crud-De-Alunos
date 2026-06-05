package com.matheus.gerenciadorDeAlunos.backend.shared.handlerException;

import com.matheus.gerenciadorDeAlunos.backend.shared.exceptions.EmailNotFoundException;
import com.matheus.gerenciadorDeAlunos.backend.shared.exceptions.IdNotFoundException;
import com.matheus.gerenciadorDeAlunos.backend.shared.exceptions.ProfessorException.ProfessorException;
import com.matheus.gerenciadorDeAlunos.backend.shared.exceptions.adminExceptions.*;
import com.matheus.gerenciadorDeAlunos.backend.shared.exceptions.alunoExceptions.AlunoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandler {

    @ExceptionHandler(AdminException.class)
    public ProblemDetail handlerAdmin(AdminException e){
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(AlunoException.class)
    public ProblemDetail handlerAluno(AlunoException e){
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(ProfessorException.class)
    public ProblemDetail handlerProfessor(ProfessorException e){
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(IdNotFoundException.class)
    public ProblemDetail handlerIdNotFound(IdNotFoundException e){
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(EmailNotFoundException.class)
    public ProblemDetail handlerEmailNotFound(EmailNotFoundException e){
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
    }
}