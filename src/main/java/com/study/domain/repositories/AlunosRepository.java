package com.study.domain.repositories;

import com.study.domain.model.Alunos;
import com.study.domain.model.Professores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlunosRepository extends JpaRepository<Alunos, Long> {

    List<Alunos> findAlunosByTutor(final Professores tutor);
}
