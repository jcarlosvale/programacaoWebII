package com.study.view.rs.v7;


import com.study.dto.v5.*;
import com.study.service.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/disciplinas")
@Slf4j
public class DisciplinaController {

    private final DisciplinaService service;

    @GetMapping("/pagination")
    public ResponseEntity<List<DisciplinaResponse>> pageable(@RequestParam(defaultValue = "0") Integer pageNo,
                                                             @RequestParam(defaultValue = "1") Integer pageSize) {
        final var response = service.getPage(pageNo, pageSize);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<DisciplinaResponse>> list() {
        final var response = service.retrieveAll();

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<DisciplinaResponse> save(@Valid final DisciplinaRequest request) {

        final var response = service.save(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisciplinaResponse> getById(@PathVariable("id") int id) {

        final var response = service.getById(id);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/titular/{idProfessor}")
    public ResponseEntity<TitularResponse> updateTitular(@PathVariable("id") int idDisciplina,
                                                         @PathVariable("idProfessor") int idProfessor) {
        final var response = service.updateTitular(idDisciplina, idProfessor);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

}
