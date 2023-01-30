package com.study.view.rs.v3;

import com.study.dto.DisciplinaDto;
import com.study.service.DisciplinaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v3/disciplinas")
@Slf4j
public class DisciplinaControllerV3 {

    private final DisciplinaService service;

    @Autowired
    public DisciplinaControllerV3(DisciplinaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<DisciplinaDto>> listaDisciplinas() {

        final List<DisciplinaDto> disciplinaDtoList = service.retrieveAll();

        return ResponseEntity.ok(disciplinaDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisciplinaDto> getDisciplina(@PathVariable("id") int id) {

        final DisciplinaDto disciplinaDto = service.getById(id);

        return ResponseEntity.ok(disciplinaDto);
    }

    @PostMapping
    public ResponseEntity<Void> saveProfessor(@RequestBody @Valid final DisciplinaDto disciplina) {

        service.save(disciplina);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<DisciplinaDto> updateDisciplina(@PathVariable("id") int id, @RequestBody @Valid DisciplinaDto disciplina) {

        service.update(id, disciplina);

        return ResponseEntity
                .ok(disciplina);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeDisciplina(@PathVariable("id") int id) {

        service.delete(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}
