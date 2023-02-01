package com.study.view.rs;

import com.study.domain.model.Alunos;
import com.study.domain.service.AlunoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = "/alunos")
@RequiredArgsConstructor
@Slf4j
public class AlunosController {

    private final AlunoService service;

    @GetMapping
    public ResponseEntity<List<Alunos>> getAll() {
        log.info("Listing alunos");

        List<Alunos> listaDeAlunos = service.retrieveAll();
        if (listaDeAlunos.isEmpty()) {
            return ResponseEntity.ok(List.of());
        } else {
            return ResponseEntity
                    .ok(new ArrayList<>(listaDeAlunos));
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Alunos> getById(@PathVariable("id") final int id) {
        log.info("Getting aluno {}", id);
        var aluno = service.getById(id);

        return ResponseEntity
                .ok(aluno);

    }

    @PostMapping
    public ResponseEntity<Alunos> save(@RequestBody final Alunos aluno) {
        if (service.getById(aluno.getId()) != null) {
            log.error("Collection contains id {}", aluno.getId());
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .build();
        } else {
            service.save(aluno);
            log.info("Inserted a new aluno {}", aluno);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(aluno);
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Alunos> update(@PathVariable("id") final int id, @RequestBody final Alunos alunos) {
        log.info("Updating aluno {}", id);
        Alunos aluno = service.getById(id);

        if (aluno == null) {
            return ResponseEntity.notFound().build();
        }

        service.update(id, alunos);

        return ResponseEntity
                .ok(alunos);

    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") final int id) {
        log.info("Deleting aluno {}", id);
        var aluno = service.getById(id);

        if (Objects.isNull(aluno)) {
            return ResponseEntity
                    .notFound()
                    .build();
        } else {
            service.delete(id);
            return ResponseEntity
                    .noContent()
                    .build();
        }
    }
}
