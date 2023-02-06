package com.study.repository;

import com.study.model.*;
import com.study.repository.projection.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Integer> {

    String GET_DISCIPLINA_FROM_PROFESSOR =
            "select " +
                    "p.professor_name as name, " +
                    "d.disciplina_name as disciplinaName " +
            "from professores p " +
            "join disciplinas d on (d.titular = p.professor_id) " +
                    "where p.professor_id = :id";
    @Query(value = GET_DISCIPLINA_FROM_PROFESSOR, nativeQuery = true )
    ProfessorDtoProjection getDisciplinaFromProfessorById(@Param("id") final int id);

}
