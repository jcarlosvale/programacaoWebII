package com.study.repository;

import com.study.entity.ProfessorEntity;
import org.springframework.data.jpa.repository.*;

public interface ProfessorRepository extends JpaRepository<ProfessorEntity, Integer> {
    
}
