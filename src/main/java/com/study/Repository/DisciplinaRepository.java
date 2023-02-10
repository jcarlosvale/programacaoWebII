package com.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.study.entity.DisciplinaEntity;

public interface DisciplinaRepository extends JpaRepository<DisciplinaEntity, Integer> {
    
}
