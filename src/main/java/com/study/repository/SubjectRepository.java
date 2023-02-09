package com.study.repository;

import com.study.model.Subject;
import com.study.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    List<Subject> findSubjectByTitularId(final Teacher titular);
}
