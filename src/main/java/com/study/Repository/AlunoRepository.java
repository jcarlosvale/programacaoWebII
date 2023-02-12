package com.study.repository;


import com.study.entity.AlunoEntity;
import com.study.entity.ProfessorEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface AlunoRepository extends JpaRepository<AlunoEntity, Integer> {

    List<AlunoEntity> findAlunosByTutor(final ProfessorEntity tutor);

}

