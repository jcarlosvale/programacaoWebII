package com.study.service.professor;

import com.study.dto.response.ProfessorResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface ProfessorService {
    ResponseEntity<Void> createProfessor(ProfessorResponse professor);
    ResponseEntity<ProfessorResponse> getById(int id);
    ResponseEntity<List<ProfessorResponse>> getAll();
    ResponseEntity<ProfessorResponse> update(int id, ProfessorResponse professor);
    ResponseEntity<Void> delete(int id);
    ResponseEntity<List<ProfessorResponse>> getByPrefix(String prefixo);
}
