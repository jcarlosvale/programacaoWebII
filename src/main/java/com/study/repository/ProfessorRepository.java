package com.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.entity.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
}