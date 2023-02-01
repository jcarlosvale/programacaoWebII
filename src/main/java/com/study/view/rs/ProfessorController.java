package com.study.view.rs;

import com.study.dto.ProfessorRequestDto;
import com.study.dto.ProfessorResponseDto;
import com.study.model.Professor;
import com.study.service.ProfessorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Using bean validations
 */

@RestController
@RequestMapping("/v3/professores")
@Slf4j
public class ProfessorController {

    private final ProfessorService service;

    @Autowired
    public ProfessorController(ProfessorService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProfessorResponseDto>> listProfessors() {

        final List<ProfessorResponseDto> professorDtoList = service.retrieveAll();

        return ResponseEntity.ok(professorDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorResponseDto> getProfessor(@PathVariable("id") int id) {

        final ProfessorResponseDto professorDto = service.getById(id);

        return ResponseEntity.ok(professorDto);
    }

    @PostMapping
    public ResponseEntity<ProfessorRequestDto> saveProfessor(@RequestBody @Valid final ProfessorRequestDto professor) {

        service.save(professor);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(professor);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessorRequestDto> updateProfessor(@PathVariable("id") int id, @RequestBody @Valid ProfessorRequestDto professor) {

        service.update(id, professor);

        return ResponseEntity
                .ok(professor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeProfessor(@PathVariable("id") int id) {

        try {
            service.delete(id);
            return ResponseEntity
                    .noContent()
                    .build();
        } catch (Exception e){
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }
}
