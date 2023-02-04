package com.study.repository;

import com.study.entity.Aluno;
import com.study.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

    List<Aluno> findAlunosByTutor(final Professor tutor);

}
