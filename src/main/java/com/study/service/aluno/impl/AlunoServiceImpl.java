package com.study.service.aluno.impl;

import com.study.domain.dto.AlunoDto;
import com.study.service.aluno.AlunoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AlunoServiceImpl implements AlunoService {

    private final Map<Integer, AlunoDto> repository = new HashMap<>();

    @Override
    public ResponseEntity<Void> createAluno(AlunoDto aluno) {
        if(repository.containsKey(aluno.getId())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        repository.put(aluno.getId(), aluno);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<AlunoDto> getById(int id) {
        AlunoDto aluno = repository.get(id);
        if(Objects.isNull(aluno)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(aluno);
    }

    @Override
    public ResponseEntity<List<AlunoDto>> getAll() {
        if(repository.isEmpty()){
            return ResponseEntity.ok(List.of());
        }
        return ResponseEntity.ok(new ArrayList<>(repository.values()));
    }

    @Override
    public ResponseEntity<AlunoDto> update(int id, AlunoDto aluno) {
        if (!repository.containsKey(aluno.getId())) {
            return ResponseEntity.notFound().build();
        }
        repository.put(id, aluno);
        return ResponseEntity.ok(aluno);
    }

    @Override
    public ResponseEntity<Void> delete(int id) {
        AlunoDto aluno = repository.get(id);
        if(Objects.isNull(aluno)){
            return ResponseEntity.notFound().build();
        }
        repository.remove(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<AlunoDto>> getByPrefix(String prefixo) {
        if(Objects.isNull(prefixo)) return getAll();
        var alunos = repository.values().stream()
                .filter(aluno -> aluno.getName().startsWith(prefixo))
                .collect(Collectors.toList());
        return ResponseEntity.ok(alunos);
    }
}
