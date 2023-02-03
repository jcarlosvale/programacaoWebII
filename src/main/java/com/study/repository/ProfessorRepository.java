package com.study.repository;

import com.study.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
}
