package com.study.view.rs.v1;

import com.study.dto.v1.*;
import lombok.extern.slf4j.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/professores")
@Slf4j
public class ProfessorControllerV1 {

    @GetMapping
    public ResponseEntity<Void> listProfessors() {
        log.info("Listing professors");
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Void> getProfessor(@PathVariable("id") int id) {
        log.info("Getting professor id-{}", id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Void> saveProfessor(@RequestBody final ProfessorDtoV1 professor) {
        log.info("Saving professor - {}", professor);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessorDtoV1> updateProfessor(@PathVariable("id") int id, @RequestBody ProfessorDtoV1 professor) {
        log.info("Updating professor id - {}", id);
        return ResponseEntity
                .ok(professor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeProfessor(@PathVariable("id") int id) {
        log.info("Deleting professor id - {}", id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
