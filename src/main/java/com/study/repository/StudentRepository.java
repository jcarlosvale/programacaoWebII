package com.study.repository;

import com.study.model.Student;
import com.study.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findStudentByTutor(final Teacher tutor);
}
