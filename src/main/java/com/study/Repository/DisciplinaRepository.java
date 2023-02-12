package com.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.study.entity.DisciplinaEntity;
import com.study.entity.ProfessorEntity;

public interface DisciplinaRepository extends JpaRepository<DisciplinaEntity, Integer> {
    DisciplinaEntity findDisciplinaByTitular(final ProfessorEntity titular);
}
