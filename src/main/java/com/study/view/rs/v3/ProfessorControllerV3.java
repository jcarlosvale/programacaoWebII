package com.study.view.rs.v3;

import com.study.dto.v2.*;
import com.study.service.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.*;

/**
 * Using bean validations
 */

@RestController
@RequestMapping("/v3/professores")
@Slf4j
public class ProfessorControllerV3 {

    private final ProfessorServiceV2 service;

    @Autowired
    public ProfessorControllerV3(ProfessorServiceV2 service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProfessorDtoV2>> listProfessors() {

        final List<ProfessorDtoV2> professorDtoV2List = service.retrieveAll();

        return ResponseEntity.ok(professorDtoV2List);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorDtoV2> getProfessor(@PathVariable("id") int id) {

        final ProfessorDtoV2 professorDtoV2 = service.getById(id);

        return ResponseEntity.ok(professorDtoV2);
    }

    @PostMapping
    public ResponseEntity<ProfessorDtoV2> saveProfessor(@RequestBody @Valid final ProfessorDtoV2 professor) {

        service.save(professor);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(professor);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessorDtoV2> updateProfessor(@PathVariable("id") int id, @RequestBody @Valid ProfessorDtoV2 professor) {

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
