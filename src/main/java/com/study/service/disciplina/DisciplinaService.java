package com.study.service.disciplina;

import com.study.dto.request.DisciplinaRequest;
import com.study.dto.response.DisciplinaResponse;

import java.util.List;

public interface DisciplinaService {

    DisciplinaResponse createDisciplina(DisciplinaRequest request);
    DisciplinaResponse getById(int id);
    List<DisciplinaResponse> getAll();
    DisciplinaResponse update(int id, DisciplinaRequest request);
    void delete(int id);


}
