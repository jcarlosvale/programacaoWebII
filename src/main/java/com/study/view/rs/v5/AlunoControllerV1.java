package com.study.view.rs.v5;


import com.study.dto.v3.*;
import com.study.service.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/alunos")
@Slf4j
public class AlunoControllerV1 {

    private final AlunoService service;

    @GetMapping
    public ResponseEntity<List<AlunoResponse>> list() {
        final var response = service.retrieveAll();

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<AlunoResponse> save(@Valid final AlunoRequest request) {
        final var response = service.save(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponse> getById(@PathVariable("id") int id) {

        final var response = service.getById(id);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/tutor/{idProfessor}")
    public ResponseEntity<TutorResponse> updateTitular(@PathVariable("id") int idAluno, @PathVariable("idProfessor") int idProfessor) {
        final var response = service.updateTutor(idAluno, idProfessor);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}
