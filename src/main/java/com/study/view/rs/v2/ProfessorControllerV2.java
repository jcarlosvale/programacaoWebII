package com.study.view.rs.v2;

import com.study.dto.v1.*;
import com.study.service.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * injecting Service
 */

@RestController
@RequestMapping("/v2/professores")
@Slf4j
public class ProfessorControllerV2 {

    private final ProfessorServiceV1 service;

    @Autowired
    public ProfessorControllerV2(ProfessorServiceV1 service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProfessorDtoV1>> listProfessors() {

        final List<ProfessorDtoV1> professorDtoV1List = service.retrieveAll();

        return ResponseEntity.ok(professorDtoV1List);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorDtoV1> getProfessor(@PathVariable("id") int id) {

        final ProfessorDtoV1 professorDtoV1 = service.getById(id);

        return ResponseEntity.ok(professorDtoV1);
    }

    @PostMapping
    public ResponseEntity<Void> saveProfessor(@RequestBody final ProfessorDtoV1 professor) {

        service.save(professor);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessorDtoV1> updateProfessor(@PathVariable("id") int id, @RequestBody ProfessorDtoV1 professor) {

        service.update(id, professor);

        return ResponseEntity
                .ok(professor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeProfessor(@PathVariable("id") int id) {

        service.delete(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}
