package com.study.service;

import java.util.Collection;
import java.util.List;

import com.study.dto.ProfessorResponse;

public interface ProfessorService {
    public Boolean save(ProfessorResponse prof);
    public Collection<ProfessorResponse> getAll();
    public List<ProfessorResponse> addAll(List<ProfessorResponse> listProf); 
}
