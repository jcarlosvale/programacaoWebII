package com.study.service;


import java.util.List;

import com.study.dto.ProfessorRequest;
import com.study.dto.ProfessorResponse;

public interface ProfessorService {
    public ProfessorResponse save(ProfessorRequest professor);
    public List<ProfessorResponse> getAll();
    public List<ProfessorResponse> addAll(List<ProfessorRequest> lstProf); 
    public ProfessorResponse getById(Integer id);
}
