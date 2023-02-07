package com.study.domain.repositories;

import com.study.domain.model.Professores;
import com.study.domain.repositories.projection.ProfessorDtoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessoresRepository extends JpaRepository<Professores, Long> {

    String GET_DISCIPLINA_FROM_PROFESSOR =
            "select " +
                    "p.professor_name as name, " +
                    "d.disciplina_name as disciplinaName " +
                    "from professores p " +
                    "join disciplinas d on (d.titular = p.professor_id) " +
                    "where p.professor_id = :id";
    @Query(value = GET_DISCIPLINA_FROM_PROFESSOR, nativeQuery = true )
    ProfessorDtoProjection getDisciplinaFromProfessorById(@Param("id") final Long id);
}
