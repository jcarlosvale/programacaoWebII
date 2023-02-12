package com.study.service;

import java.util.List;
import com.study.dto.DisciplinaResponse;

public interface DisciplinaService {
    public DisciplinaResponse save(DisciplinaResponse disciplina);
    public List<DisciplinaResponse> getAll(); 
    public DisciplinaResponse getById(Integer id);  
    public DisciplinaResponse updateTitular(final Integer idDisciplina, final Integer idTitular);
}
