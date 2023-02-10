package com.study.service.disciplina;

import com.study.dto.request.DisciplinaRequest;
import com.study.dto.response.DisciplinaResponse;
import com.study.dto.response.TitularResponse;

import java.util.List;

public interface DisciplinaService {

    DisciplinaResponse createDisciplina(DisciplinaRequest request);
    DisciplinaResponse getById(int id);
    List<DisciplinaResponse> getAll();
    TitularResponse update(int id, int idProfessor);
    void delete(int id);
    DisciplinaResponse getDisciplinaByProfessorId(int id);

}
