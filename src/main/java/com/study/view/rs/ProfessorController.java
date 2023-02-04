package com.study.view.rs;

import com.study.domain.dto.*;
import com.study.service.AlunoService;
import com.study.service.CursoService;
import com.study.service.ProfessorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/professores")
@RequiredArgsConstructor
@Slf4j
public class ProfessorController {

    private final ProfessorService professorService;

    private final AlunoService alunoService;

    private final CursoService cursoService;

    @PostMapping
    public ResponseEntity<ProfessorResponseDto> save(@RequestBody @Valid final ProfessorRequestDto professor) {

        ProfessorResponseDto professorCreated =  professorService.save(professor);
        log.info("Inserted a new professor {}", professor);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(professorCreated);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProfessorResponseDto> getById(@PathVariable("id") final int id) {
        log.info("Getting professor {}", id);
        var professor = professorService.getById(id);
        return ResponseEntity
                .ok(professor);
    }

    @GetMapping
    public ResponseEntity<List<ProfessorResponseDto>> getAll() {
        log.info("Listing professores");

        return ResponseEntity
                .ok(professorService.getAll());
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ProfessorResponseDto> update(@PathVariable("id") final int id, @RequestBody @Valid final ProfessorRequestDto professor) {
        log.info("Updating professor {}", id);

        ProfessorResponseDto professorReponse =  professorService.update(id, professor);
        return ResponseEntity
                .ok(professorReponse);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") final int id) {
        log.info("Deleting professor {}", id);

        professorService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }

    @GetMapping("/{id}/tutorados")
    public ResponseEntity<List<AlunoResponseDto>> getTutorados(@PathVariable("id") int id) {

        final var response = alunoService.getTutoradosByProfessorId(id);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/curso-titular")
    public ResponseEntity<CursoResponseDto> getCursoTitular(@PathVariable("id") int id) {

        final var response = cursoService.getTitularByProfessorId(id);

        return ResponseEntity.ok(response);
    }
}
