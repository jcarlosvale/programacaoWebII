package com.study.view.rs.v5;

import com.study.dto.v3.*;
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
@RequestMapping("/v5/professores")
@Slf4j
public class ProfessorControllerV5 {

    private final ProfessorService service;
    private final AlunoService alunoService;

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
}
