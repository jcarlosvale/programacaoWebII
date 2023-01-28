package com.study.view.rs;

import com.study.domain.dto.*;
import com.study.domain.service.AlunoService;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.*;

@RestController
@RequestMapping(path = "/alunos")
@RequiredArgsConstructor
@Slf4j
public class AlunosController {

    private final AlunoService service;


    //@GetMapping  comentado para evitar conflito de endpoint com request param abaixo
    public ResponseEntity<List<AlunosDto>> getAll() {
        log.info("Listing alunos");

        List<AlunosDto> listaDeAlunos = service.retrieveAll();
        if (listaDeAlunos.isEmpty()) {
            return ResponseEntity.ok(List.of());
        }
        else {
            return ResponseEntity
                    .ok(new ArrayList<>(listaDeAlunos));
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<AlunosDto> getById(@PathVariable("id") final int id) {
        log.info("Getting aluno {}", id);
        var aluno = service.getById(id);
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
    public ResponseEntity<AlunosDto> save(@RequestBody final AlunosDto aluno) {
        if (service.getById(aluno.getId()) != null) {
            log.error("Collection contains id {}", aluno.getId());
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .build();
        }
        else {
            service.save(aluno);
            log.info("Inserted a new aluno {}", aluno);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(aluno);
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<AlunosDto> update(@PathVariable("id") final int id, @RequestBody final AlunosDto alunosDto) {
        log.info("Updating aluno {}", id);
        AlunosDto aluno = service.getById(id);

        if (aluno == null) {
            return ResponseEntity.notFound().build();
        }

        service.update(id, alunosDto);

        return ResponseEntity
                    .ok(alunosDto);

    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") final int id) {
        log.info("Deleting aluno {}", id);
        var aluno = service.getById(id);

        if (Objects.isNull(aluno)) {
            return ResponseEntity
                    .notFound()
                    .build();
        }
        else {
            service.delete(id);
            return ResponseEntity
                    .noContent()
                    .build();
        }
    }
}
