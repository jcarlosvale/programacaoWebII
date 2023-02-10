package com.study.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.*;
import lombok.extern.slf4j.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.dto.AlunoResponse;
import com.study.service.AlunoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/alunos")
@RequiredArgsConstructor
@Slf4j
public class AlunoController {
    
    @Autowired
    private final AlunoService alunoService;

     //@GetMapping  comentado para evitar conflito de endpoint com request param abaixo
     public ResponseEntity<List<AlunoResponse>> getAll() {
        log.info("Listing alunos....");
            return ResponseEntity.ok(new ArrayList<AlunoResponse>(alunoService.getAll()));
        }

    @GetMapping
    public ResponseEntity<List<AlunoResponse>> getByPrefix(@RequestParam(value = "prefixo", required = false) final String prefixo) {
        log.info("Obtendo alunos com o prefixo {}", prefixo);
        if(Objects.isNull(prefixo)) return getAll();
        var selectedAlunos =
                alunoService.getByPrefix(prefixo);
        return  ResponseEntity.ok(selectedAlunos);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<AlunoResponse> getById(@PathVariable("id") final int id) {
        log.info("Getting aluno {}", id);
        var aluno = alunoService.getById(id);
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
            log.info("Inserido novo aluno {}", aluno);
            alunoService.save(aluno);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(aluno);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<AlunoResponse> update(@PathVariable("id") final int id, @RequestBody final AlunoResponse alunoResponse) {
        log.info("Atualizando aluno {}", id);
            alunoService.update(id, alunoResponse);
            return ResponseEntity
                    .ok(alunoResponse);
        }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") final int id) {
        log.info("Deleting aluno {}", id);
        var aluno = alunoService.getById(id);
        if (Objects.isNull(aluno)) {
            return ResponseEntity
                    .notFound()
                    .build();
        }
        else {
            alunoService.delete(id);
            return ResponseEntity
                    .noContent()
                    .build();
        }
    }

    @PutMapping(path = "/updatetutor/{id_aluno}/{id_prof}")
    public AlunoResponse updateTutor(@PathVariable final int id_aluno, @PathVariable final int id_prof){
        return alunoService.updateTutor(id_aluno, id_prof);
    }
    
}
