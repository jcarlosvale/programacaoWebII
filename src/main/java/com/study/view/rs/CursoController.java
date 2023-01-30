package com.study.view.rs;

import com.study.domain.dto.CursoDto;
import com.study.domain.dto.ProfessorDto;
import com.study.service.CursoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/cursos")
@RequiredArgsConstructor
@Slf4j
public class CursoController {

    private final CursoService cursoService;


    @PostMapping
    public ResponseEntity<CursoDto> save(@RequestBody @Valid final CursoDto curso) {

            cursoService.save(curso);
            log.info("Inserted a new curso {}", curso);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(curso);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CursoDto> getById(@PathVariable("id") final int id) {
        log.info("Getting curso {}", id);
        var curso = cursoService.getById(id);
            return ResponseEntity
                    .ok(curso);
    }

    @GetMapping
    public ResponseEntity<List<CursoDto>> getAll() {
        log.info("Listing cursos");

            return ResponseEntity
                    .ok(cursoService.getAll());
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<CursoDto> update(@PathVariable("id") final int id, @RequestBody @Valid final CursoDto curso) {
        log.info("Updating curso {}", id);

            cursoService.update(id, curso);
            return ResponseEntity
                    .ok(curso);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") final int id) {
        log.info("Deleting curso {}", id);

            cursoService.delete(id);
            return ResponseEntity
                    .noContent()
                    .build();
    }
}
