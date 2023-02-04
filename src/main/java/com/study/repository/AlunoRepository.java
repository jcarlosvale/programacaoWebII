package com.study.repository;

import com.study.model.AlunoModel;
import com.study.model.ProfessorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlunoRepository extends JpaRepository<AlunoModel, Integer> {

    List<AlunoModel> findAlunosByTutor(final ProfessorModel tutor);
}
