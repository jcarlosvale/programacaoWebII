package com.study.domain.repositories;

import com.study.domain.model.Disciplinas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplinas, Long> {
}
