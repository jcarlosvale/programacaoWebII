package com.study.service.disciplina;

import com.study.domain.dto.DisciplinaDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DisciplinaService {

    ResponseEntity<Void> createDisciplina(DisciplinaDto disciplina);
    ResponseEntity<DisciplinaDto> getById(int id);
    ResponseEntity<List<DisciplinaDto>> getAll();
    ResponseEntity<DisciplinaDto> update(int id, DisciplinaDto disciplina);
    ResponseEntity<Void> delete(int id);
    ResponseEntity<List<DisciplinaDto>> getByPrefix(String prefixo);

}
