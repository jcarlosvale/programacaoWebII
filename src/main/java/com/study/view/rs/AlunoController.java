package com.study.view.rs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.study.domain.dto.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping(path = "/alunos")
@RequiredArgsConstructor
@Slf4j
public class AlunoController {
    
    private final Map<Integer, AlunoDto> repository;

     //@GetMapping  comentado para evitar conflito de endpoint com request param abaixo
     public ResponseEntity<List<AlunoDto>> getAll() {
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
    public ResponseEntity<List<AlunoDto>> getByPrefix(@RequestParam(value = "prefixo", required = false) final String prefixo) {
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
    public ResponseEntity<AlunoDto> getById(@PathVariable("id") final int id) {
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

    @PostMapping
    public ResponseEntity<AlunoDto> save(@RequestBody final AlunoDto aluno) {
        if (repository.containsKey(aluno.getId())) {
            log.error("Coleção contem id {}", aluno.getId());
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .build();
        }
        else {
            repository.put(aluno.getId(), aluno);
            log.info("Inserido novo aluno {}", aluno);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(aluno);
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<AlunoDto> update(@PathVariable("id") final int id, @RequestBody final AlunoDto alunoDto) {
        log.info("Atualizando aluno {}", id);
        if (!repository.containsKey(alunoDto.getId())) {
            return ResponseEntity
                    .notFound()
                    .build();
        }
        else {
            repository.put(id, alunoDto);
            return ResponseEntity
                    .ok(alunoDto);
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
