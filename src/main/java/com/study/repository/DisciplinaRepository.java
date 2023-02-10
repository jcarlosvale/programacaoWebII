package com.study.repository;

import com.study.entity.Disciplina;
import com.study.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Integer> {

    Optional<Disciplina> findByTitular(@Param("titular") Professor professor);

}
