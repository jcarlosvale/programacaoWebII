package com.study.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.study.Entity.ProfessorEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<ProfessorEntity, Integer> {
    
}
