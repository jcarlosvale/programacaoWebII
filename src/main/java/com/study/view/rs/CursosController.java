package com.study.view.rs;

import com.study.domain.dto.AlunosRequest;
import com.study.domain.dto.AlunosResponse;
import com.study.domain.dto.CursosRequest;
import com.study.domain.dto.CursosResponse;
import com.study.domain.service.CursoService;
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
public class CursosController {

    private final CursoService service;

    @GetMapping
    public ResponseEntity<List<CursosResponse>> listCursos() {
        final List<CursosResponse> cursoDtoList = service.retrieveAll();
        return ResponseEntity.ok(cursoDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursosResponse> getCurso(@PathVariable("id") Long id) {
        final CursosResponse cursoDto = service.getById(id);
        return ResponseEntity.ok(cursoDto);
    }

    @PostMapping
    public ResponseEntity<CursosResponse> saveCurso(@RequestBody @Valid final CursosRequest curso) {
        var response = service.save(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CursosResponse> updateCurso(@PathVariable("id") Long id,
                                                      @RequestBody @Valid CursosRequest curso) {

        var response = service.update(id, curso);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeCurso(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
