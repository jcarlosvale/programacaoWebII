package com.study.repository;


import com.study.entity.AlunoEntity;
import com.study.entity.ProfessorEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface AlunoRepository extends JpaRepository<AlunoEntity, Integer> {

    List<AlunoEntity> findAlunosByTutor(final ProfessorEntity tutor);

   /* 
    @Modifying
    @Query("update ALUNOS u set u.NOME = ?1, u.EMAIL = ?2, u.CPF = ?3, u.IDADE = ?4, u.MATRICULA = ?5, u.SEXO = ?6 where u.ID = ?7")
    public static AlunoEntity alunoUpdate(String nome, String email, String cpf, Integer idade, String matricula, String sexo, Integer id){
        return AlunoEntity.builder().nome(nome).email(email).cpf(cpf).idade(idade).matricula(matricula).sexo(sexo).id(id).build();
    };
    */
}

