package com.study.service.professor;

import com.study.domain.dto.ProfessorDto;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface ProfessorService {
    ResponseEntity<Void> createProfessor(ProfessorDto professor);
    ResponseEntity<ProfessorDto> getById(int id);
    ResponseEntity<List<ProfessorDto>> getAll();
    ResponseEntity<ProfessorDto> update(int id, ProfessorDto professor);
    ResponseEntity<Void> delete(int id);
    ResponseEntity<List<ProfessorDto>> getByPrefix(String prefixo);
}
