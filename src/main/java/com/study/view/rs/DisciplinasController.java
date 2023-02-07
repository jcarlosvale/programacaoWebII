package com.study.view.rs;

import com.study.domain.dto.DisciplinasRequest;
import com.study.domain.dto.DisciplinasResponse;
import com.study.domain.dto.ProfessoresResponse;
import com.study.domain.service.DisciplinaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/disciplinas")
@RequiredArgsConstructor
@Slf4j
public class DisciplinasController {

    private final DisciplinaService service;

    @GetMapping("/pagination")
    public ResponseEntity<List<DisciplinasResponse>> pageable(@RequestParam(defaultValue = "0") Integer pageNo,
                                                             @RequestParam(defaultValue = "1") Integer pageSize) {
        final var response = service.getPage(pageNo, pageSize);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<DisciplinasResponse>> listDisciplinas() {
        final var response = service.retrieveAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisciplinasResponse> getCurso(@PathVariable("id") Long id) {
        final var response = service.getById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<DisciplinasResponse> saveCurso(@RequestBody @Valid final DisciplinasRequest request) {
        final var response = service.save(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DisciplinasResponse> updateCurso(@PathVariable("id") Long id,
                                                           @RequestBody @Valid DisciplinasRequest curso) {

        var response = service.update(id, curso);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeCurso(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/titular/{idProfessor}")
    public ResponseEntity<ProfessoresResponse> updateTitular(@PathVariable("id") Long idDisciplina,
                                                             @PathVariable("idProfessor") Long idProfessor) {
        final var response = service.updateTitular(idDisciplina, idProfessor);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping(value = "/professores/{idProfessor}/disciplina")
    public ResponseEntity<DisciplinasResponse> getDisciplinaByProgessor(@PathVariable Long idProfessor){
        var disciplina = service.getDisciplinaByProfessorId(idProfessor);

        return ResponseEntity.status(HttpStatus.OK).body(disciplina);
    }
}
