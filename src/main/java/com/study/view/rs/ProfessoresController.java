package com.study.view.rs;

import com.study.domain.dto.AlunosResponse;
import com.study.domain.dto.DisciplinasResponse;
import com.study.domain.dto.ProfessoresRequest;
import com.study.domain.dto.ProfessoresResponse;
import com.study.domain.repositories.projection.ProfessorDtoProjection;
import com.study.domain.service.AlunoService;
import com.study.domain.service.DisciplinaService;
import com.study.domain.service.ProfessorService;
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
public class ProfessoresController {

    private final ProfessorService service;
    private final AlunoService alunoService;
    private final DisciplinaService disciplinaService;

    @GetMapping
    public ResponseEntity<List<ProfessoresResponse>> listProfessors() {
        final List<ProfessoresResponse> professorDtoList = service.retrieveAll();
        return ResponseEntity.ok(professorDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessoresResponse> getProfessor(@PathVariable("id") Long id) {
        final ProfessoresResponse professorDto = service.getById(id);
        return ResponseEntity.ok(professorDto);
    }

    @PostMapping
    public ResponseEntity<ProfessoresResponse> saveProfessor(@RequestBody @Valid final ProfessoresRequest professor) {
        var response = service.save(professor);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessoresResponse> updateProfessor(@PathVariable("id") Long id,
                                                               @RequestBody @Valid ProfessoresRequest professor) {

        var response = service.update(id, professor);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeProfessor(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/tutorados")
    public ResponseEntity<List<AlunosResponse>> getTutorados(@PathVariable("id") Long id) {
        final var response = alunoService.getTutoradosByProfessorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/disciplina")
    public ResponseEntity<DisciplinasResponse> getDisciplina(@PathVariable("id") Long id) {
        final var response = disciplinaService.getDisciplinaByProfessorId(id);
        return ResponseEntity.ok(response);
    }

//    @GetMapping("/{id}/projection")
//    public ResponseEntity<ProfessorDtoProjection> getDisciplinaByProfessorProjection(@PathVariable("id") Long id) {
//
//        final var response = service.getById(id);
//
//        return ResponseEntity.ok(response);
//    }
}
