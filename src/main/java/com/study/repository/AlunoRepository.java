package com.study.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.study.entity.Aluno;
import com.study.entity.Professor;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

    List<Aluno> findAlunosByTutor(final Professor tutor);

}