package com.study.repository;

import com.study.entity.Disciplina;
import com.study.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Integer> {

    List<Disciplina> findByTitular(final Professor titular);

}
