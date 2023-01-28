package com.study.service.professor.impl;

import com.study.domain.dto.ProfessorDto;
import com.study.service.professor.ProfessorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProfessorServiceImpl implements ProfessorService {

    private final Map<Integer, ProfessorDto> repository = new HashMap<>();

    @Override
    public ResponseEntity<Void> createProfessor(ProfessorDto professor) {
        if(repository.containsKey(professor.getId())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        repository.put(professor.getId(), professor);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<ProfessorDto> getById(int id) {
        ProfessorDto professor = repository.get(id);
        if(Objects.isNull(professor)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(professor);
    }

    @Override
    public ResponseEntity<List<ProfessorDto>> getAll() {
        if(repository.isEmpty()){
            return ResponseEntity.ok(List.of());
        }
        return ResponseEntity.ok(new ArrayList<>(repository.values()));
    }

    @Override
    public ResponseEntity<ProfessorDto> update(int id, ProfessorDto professor) {
        if (!repository.containsKey(professor.getId())) {
            return ResponseEntity.notFound().build();
        }
        repository.put(id, professor);
        return ResponseEntity.ok(professor);
    }

    @Override
    public ResponseEntity<Void> delete(int id) {
        ProfessorDto professor = repository.get(id);
        if(Objects.isNull(professor)){
            return ResponseEntity.notFound().build();
        }
        repository.remove(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<ProfessorDto>> getByPrefix(String prefixo) {
        if(Objects.isNull(prefixo)) return getAll();
        var professores = repository.values().stream()
                .filter(professor -> professor.getNome().startsWith(prefixo))
                .collect(Collectors.toList());
        return ResponseEntity.ok(professores);
    }
}
