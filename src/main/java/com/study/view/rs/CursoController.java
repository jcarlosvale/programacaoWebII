package com.study.view.rs;

import com.study.domain.dto.*;
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
    public ResponseEntity<CursoResponseDto> save(@RequestBody @Valid final CursoRequestDto curso) {

        CursoResponseDto cursoCreated =  cursoService.save(curso);
        log.info("Inserted a new curso {}", curso);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(cursoCreated);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CursoResponseDto> getById(@PathVariable("id") final int id) {
        log.info("Getting curso {}", id);
        var curso = cursoService.getById(id);
        return ResponseEntity
                .ok(curso);
    }

    @GetMapping
    public ResponseEntity<List<CursoResponseDto>> getAll() {
        log.info("Listing cursos");

        return ResponseEntity
                .ok(cursoService.getAll());
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<CursoResponseDto> update(@PathVariable("id") final int id, @RequestBody @Valid final CursoRequestDto curso) {
        log.info("Updating curso {}", id);

        CursoResponseDto cursoReponse =  cursoService.update(id, curso);
        return ResponseEntity
                .ok(cursoReponse);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") final int id) {
        log.info("Deleting curso {}", id);

        cursoService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }

    @PatchMapping("/{id}/tutor/{idProfessor}")
    public ResponseEntity<TitularResponse> updateTitular(@PathVariable("id") int idCurso, @PathVariable("idProfessor") int idProfessor) {
        final var response = cursoService.updateTitular(idCurso, idProfessor);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}
