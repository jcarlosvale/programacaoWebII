package com.study.service;

import java.util.List;

import com.study.dto.request.ProfessorRequest;
import com.study.dto.response.ProfessorResponse;


public interface ProfessorService {
    ProfessorResponse createProfessor(ProfessorRequest request);
    ProfessorResponse getById(int id);
    List<ProfessorResponse> getAll();
    ProfessorResponse update(int id, ProfessorRequest request);
    void delete(int id);
}