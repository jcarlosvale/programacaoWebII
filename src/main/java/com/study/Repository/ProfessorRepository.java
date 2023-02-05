package com.study.repository;

import com.study.model.*;
import org.springframework.data.jpa.repository.*;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
}
