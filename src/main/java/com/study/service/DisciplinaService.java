package com.study.service;

import java.util.List;

import com.study.dto.request.DisciplinaRequest;
import com.study.dto.response.DisciplinaResponse;

public interface DisciplinaService {

    DisciplinaResponse createDisciplina(DisciplinaRequest request);
    DisciplinaResponse getById(int id);
    List<DisciplinaResponse> getAll();
    DisciplinaResponse update(int id, DisciplinaRequest request);
    void delete(int id);


}