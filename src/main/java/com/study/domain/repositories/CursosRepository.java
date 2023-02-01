package com.study.domain.repositories;

import com.study.domain.model.Cursos;
import com.study.domain.model.Professores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursosRepository extends JpaRepository<Cursos, Long> {
}
