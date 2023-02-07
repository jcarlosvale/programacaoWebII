package com.study.domain.repositories;

import com.study.domain.model.Disciplinas;
import com.study.domain.model.Professores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplinas, Long> {

    String SELECT_ALL =
            "select * from disciplinas";
    @Query(nativeQuery = true, value = SELECT_ALL)
    List<Disciplinas> listAll();

    String SELECT_BY_ID =
            "select * from disciplinas where disciplina_id = :id";
    @Query(nativeQuery = true, value = SELECT_BY_ID)
    Optional<Disciplinas> findByIdNativeQuery(@Param("id") Long id);

    String SELECT_BY_TITULAR =
            "select d from Disciplinas as d where d.titular = :titular";
    @Query(value = SELECT_BY_TITULAR)
    Optional<Disciplinas> findByTitular(@Param("titular") Professores professor);
}
