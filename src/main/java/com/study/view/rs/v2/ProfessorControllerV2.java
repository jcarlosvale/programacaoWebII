package com.study.view.rs.v2;

import com.study.domain.dto.v1.*;
import com.study.service.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/v2/professores")
@Slf4j
public class ProfessorControllerV2 {

    private final ProfessorService service;

    @Autowired
    public ProfessorControllerV2(ProfessorService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProfessorDto>> listProfessors() {

        final List<ProfessorDto> professorDtoList = service.retrieveAll();

        return ResponseEntity.ok(professorDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorDto> getProfessor(@PathVariable("id") int id) {

        final ProfessorDto professorDto = service.getById(id);

        return ResponseEntity.ok(professorDto);
    }

    @PostMapping
    public ResponseEntity<Void> saveProfessor(@RequestBody final ProfessorDto professor) {

        service.save(professor);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessorDto> updateProfessor(@PathVariable("id") int id, @RequestBody ProfessorDto professor) {

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
