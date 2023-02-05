package com.study.view.rs.v7;

import com.study.dto.v3.*;
import com.study.dto.v5.*;
import com.study.repository.projection.*;
import com.study.service.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.*;

/**
 * Using service with repository
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/v6/professores")
@Slf4j
public class ProfessorControllerV6 {

    private final ProfessorService service;
    private final AlunoService alunoService;

    private final DisciplinaService disciplinaService;

    @GetMapping
    public ResponseEntity<List<ProfessorResponse>> listProfessors() {

        final List<ProfessorResponse> professorDtoList = service.retrieveAll();

        return ResponseEntity.ok(professorDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorResponse> getProfessor(@PathVariable("id") int id) {

        final ProfessorResponse professorDto = service.getById(id);

        return ResponseEntity.ok(professorDto);
    }

    @PostMapping
    public ResponseEntity<ProfessorResponse> saveProfessor(@RequestBody @Valid final ProfessorRequest professor) {

        var response = service.save(professor);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessorResponse> updateProfessor(@PathVariable("id") int id, @RequestBody @Valid ProfessorRequest professor) {

        var response = service.update(id, professor);

        return ResponseEntity
                .ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeProfessor(@PathVariable("id") int id) {

        service.delete(id);

        return ResponseEntity
                .noContent()
                .build();
    }

    @GetMapping("/{id}/tutorados")
    public ResponseEntity<List<AlunoResponse>> getTutorados(@PathVariable("id") int id) {

        final var response = alunoService.getTutoradosByProfessorId(id);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/disciplina")
    public ResponseEntity<DisciplinaResponse> getDisciplina(@PathVariable("id") int id) {

        final var response = disciplinaService.getDisciplinaByProfessorId(id);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/projection")
    public ResponseEntity<ProfessorDtoProjection> getDisciplinaByProfessorProjection(@PathVariable("id") int id) {

        final var response = service.getDisciplinaByProfessorId(id);

        return ResponseEntity.ok(response);
    }
}
