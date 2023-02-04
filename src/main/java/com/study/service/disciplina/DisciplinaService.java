package com.study.service.disciplina;

import com.study.dto.response.DisciplinaResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DisciplinaService {

    ResponseEntity<Void> createDisciplina(DisciplinaResponse disciplina);
    ResponseEntity<DisciplinaResponse> getById(int id);
    ResponseEntity<List<DisciplinaResponse>> getAll();
    ResponseEntity<DisciplinaResponse> update(int id, DisciplinaResponse disciplina);
    ResponseEntity<Void> delete(int id);
    ResponseEntity<List<DisciplinaResponse>> getByPrefix(String prefixo);

}
