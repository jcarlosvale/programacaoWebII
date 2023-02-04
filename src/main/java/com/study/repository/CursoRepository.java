package com.study.repository;

import com.study.model.AlunoModel;
import com.study.model.CursoModel;
import com.study.model.ProfessorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursoRepository extends JpaRepository<CursoModel, Integer> {

    CursoModel findCursoByTitular(final ProfessorModel titular);
}
