package com.study.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.dto.AlunoResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/alunos")
@RequiredArgsConstructor
@Slf4j
public class AlunoController {
    
    private final Map<Integer, AlunoResponse> repository;

     //@GetMapping  comentado para evitar conflito de endpoint com request param abaixo
     public ResponseEntity<List<AlunoResponse>> getAll() {
        log.info("Listing alunos");
        if (repository.isEmpty()) {
            return ResponseEntity.ok(List.of());
        }
        else {
            return ResponseEntity
                    .ok(new ArrayList<>(repository.values()));
        }
    }

    @GetMapping
    public ResponseEntity<List<AlunoResponse>> getByPrefix(@RequestParam(value = "prefixo", required = false) final String prefixo) {
        log.info("Obtendo alunos com o prefixo {}", prefixo);
        if(Objects.isNull(prefixo)) return getAll();
        var selectedAlunos =
                repository.values().stream()
                .filter(aluno -> aluno.getNome().startsWith(prefixo))
                .collect(Collectors.toList());
        return ResponseEntity
                .ok(selectedAlunos);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<AlunoResponse> getById(@PathVariable("id") final int id) {
        log.info("Getting aluno {}", id);
        var aluno = repository.get(id);
        if (Objects.isNull(aluno)) {
            return ResponseEntity
                    .notFound()
                    .build();
        }
        else {
            return ResponseEntity
                    .ok(aluno);
        }
    }

    @PostMapping(path="/save")
    public ResponseEntity<AlunoResponse> save(@RequestBody final AlunoResponse aluno) {
        if (repository.containsKey(aluno.getId())) {
            log.error("Coleção contem id {}", aluno.getId());
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .build();
        }
        else {
            log.info(String.valueOf(aluno.getId()));
            repository.put(aluno.getId(), aluno);
            log.info("Inserido novo aluno {}", aluno);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(aluno);
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<AlunoResponse> update(@PathVariable("id") final int id, @RequestBody final AlunoResponse AlunoResponse) {
        log.info("Atualizando aluno {}", id);
        if (!repository.containsKey(AlunoResponse.getId())) {
            return ResponseEntity
                    .notFound()
                    .build();
        }
        else {
            repository.put(id, AlunoResponse);
            return ResponseEntity
                    .ok(AlunoResponse);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") final int id) {
        log.info("Deleting aluno {}", id);
        var aluno = repository.get(id);
        if (Objects.isNull(aluno)) {
            return ResponseEntity
                    .notFound()
                    .build();
        }
        else {
            repository.remove(id);
            return ResponseEntity
                    .noContent()
                    .build();
        }
    }
    
}
