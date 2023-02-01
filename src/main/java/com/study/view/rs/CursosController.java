package com.study.view.rs;

import com.study.domain.model.Cursos;
import com.study.domain.service.CursoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = "/cursos")
@RequiredArgsConstructor
@Slf4j
public class CursosController {

    private final CursoService service;

    @GetMapping
    public ResponseEntity<List<Cursos>> getAll() {
        log.info("Listing cursos");

        List<Cursos> listaDeCursos = service.retrieveAll();
        if (listaDeCursos.isEmpty()) {
            return ResponseEntity.ok(List.of());
        } else {
            return ResponseEntity
                    .ok(new ArrayList<>(listaDeCursos));
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Cursos> getById(@PathVariable("id") final int id) {
        log.info("Getting curso {}", id);
        var curso = service.getById(id);
        if (Objects.isNull(curso)) {
            return ResponseEntity
                    .notFound()
                    .build();
        } else {
            return ResponseEntity
                    .ok(curso);
        }
    }

    @PostMapping
    public ResponseEntity<Cursos> save(@RequestBody @Valid final Cursos curso) {
        if (service.getById(curso.getId()) != null) {
            log.error("Collection contains id {}", curso.getId());
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .build();
        } else {
            service.save(curso);
            log.info("Inserted a new curso {}", curso);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(curso);
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Cursos> update(@PathVariable("id") final int id, @RequestBody @Valid final Cursos cursoDto) {
        log.info("Updating aluno {}", id);
        Cursos curso = service.getById(id);

        if (curso == null) {
            return ResponseEntity.notFound().build();
        }

        service.update(id, cursoDto);

        return ResponseEntity
                .ok(cursoDto);

    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") final int id) {
        log.info("Deleting curso {}", id);
        var curso = service.getById(id);

        if (Objects.isNull(curso)) {
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
