package com.study.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.entity.Disciplina;
import com.study.entity.Professor;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Integer> {

    List<Disciplina> findByTitular(final Professor titular);

}