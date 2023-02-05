package com.study.repository;

import com.study.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface DisciplinaRepository extends PagingAndSortingRepository<Disciplina, Integer> {

    String SELECT_ALL =
            "select * from disciplinas";
    @Query(nativeQuery = true, value = SELECT_ALL)
    List<Disciplina> listAll();

    String SELECT_BY_ID =
            "select * from disciplinas where disciplina_id = :id";
    @Query(nativeQuery = true, value = SELECT_BY_ID)
    Optional<Disciplina> findByIdNativeQuery(@Param("id") int id);



    String SELECT_BY_TITULAR =
            "select d from Disciplina as d where d.titular = :titular";
    @Query(value = SELECT_BY_TITULAR)
    Optional<Disciplina> findByTitular(@Param("titular") Professor professor);
}
