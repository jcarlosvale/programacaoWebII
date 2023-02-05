package com.study.Service;

import java.util.Collection;
import java.util.List;

import com.study.domain.dto.ProfessorDto;

public interface ProfessorService {
    public Boolean save(ProfessorDto prof);
    public Collection<ProfessorDto> getAll();
    public List<ProfessorDto> addAll(List<ProfessorDto> listProf); 
}
