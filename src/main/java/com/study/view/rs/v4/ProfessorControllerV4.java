package com.study.view.rs.v4;

import com.study.dto.v3.*;
import com.study.service.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.*;

/**
 * Using service with repository
 */

@RestController
@RequestMapping("/v4/professores")
@Slf4j
public class ProfessorControllerV4 {

    private final ProfessorService service;

    @Autowired
    public ProfessorControllerV4(ProfessorService service) {
        this.service = service;
    }

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
}
