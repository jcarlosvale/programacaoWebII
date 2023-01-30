package com.study.view.rs;

import com.study.domain.dto.CursosDto;
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

    //@GetMapping  comentado para evitar conflito de endpoint com request param abaixo
    public ResponseEntity<List<CursosDto>> getAll() {
        log.info("Listing cursos");

        List<CursosDto> listaDeCursos = service.retrieveAll();
        if (listaDeCursos.isEmpty()) {
            return ResponseEntity.ok(List.of());
        } else {
            return ResponseEntity
                    .ok(new ArrayList<>(listaDeCursos));
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CursosDto> getById(@PathVariable("id") final int id) {
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
    public ResponseEntity<CursosDto> save(@RequestBody @Valid final CursosDto curso) {
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
    public ResponseEntity<CursosDto> update(@PathVariable("id") final int id, @RequestBody @Valid final CursosDto cursoDto) {
        log.info("Updating aluno {}", id);
        CursosDto curso = service.getById(id);

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
