package com.study.service.disciplina.impl;

import com.study.dto.response.DisciplinaResponse;
import com.study.service.disciplina.DisciplinaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DisciplinaServiceImpl implements DisciplinaService {

    private Map<Integer, DisciplinaResponse> repository = new HashMap<>();

    @Override
    public ResponseEntity<Void> createDisciplina(DisciplinaResponse disciplina) {
        if (repository.containsKey(disciplina.getId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        repository.put(disciplina.getId(), disciplina);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<DisciplinaResponse> getById(int id) {
        DisciplinaResponse disciplina = repository.get(id);
        if (Objects.isNull(disciplina)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(disciplina);
    }

    @Override
    public ResponseEntity<List<DisciplinaResponse>> getAll() {
        if (repository.isEmpty()) {
            return ResponseEntity.ok(List.of());
        }
        return ResponseEntity.ok(new ArrayList<>(repository.values()));
    }

    @Override
    public ResponseEntity<DisciplinaResponse> update(int id, DisciplinaResponse disciplina) {
        if (!repository.containsKey(disciplina.getId())) {
            return ResponseEntity.notFound().build();
        }
        repository.put(id, disciplina);
        return ResponseEntity.ok(disciplina);
    }

    @Override
    public ResponseEntity<Void> delete(int id) {
        DisciplinaResponse disciplina = repository.get(id);
        if (Objects.isNull(disciplina)) {
            return ResponseEntity.notFound().build();
        }
        repository.remove(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<DisciplinaResponse>> getByPrefix(String prefix) {
        if (Objects.isNull(prefix)) {
            return getAll();
        }
        var disciplinas =
                repository.values().stream()
                        .filter(disciplina -> disciplina.getName().startsWith(prefix))
                        .collect(Collectors.toList());
        return ResponseEntity.ok(disciplinas);
    }
}
