package com.study.view.rs;

import com.study.domain.dto.AlunoRequestDto;
import com.study.domain.dto.AlunoResponseDto;
import com.study.domain.dto.ProfessorRequestDto;
import com.study.domain.dto.ProfessorResponseDto;
import com.study.service.ProfessorService;
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
public class ProfessorController {

    private final ProfessorService professorService;

    @PostMapping
    public ResponseEntity<ProfessorResponseDto> save(@RequestBody @Valid final ProfessorRequestDto professor) {

        ProfessorResponseDto professorCreated =  professorService.save(professor);
        log.info("Inserted a new professor {}", professor);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(professorCreated);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProfessorResponseDto> getById(@PathVariable("id") final int id) {
        log.info("Getting professor {}", id);
        var professor = professorService.getById(id);
        return ResponseEntity
                .ok(professor);
    }

    @GetMapping
    public ResponseEntity<List<ProfessorResponseDto>> getAll() {
        log.info("Listing professores");

        return ResponseEntity
                .ok(professorService.getAll());
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ProfessorResponseDto> update(@PathVariable("id") final int id, @RequestBody @Valid final ProfessorRequestDto professor) {
        log.info("Updating professor {}", id);

        ProfessorResponseDto professorReponse =  professorService.update(id, professor);
        return ResponseEntity
                .ok(professorReponse);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") final int id) {
        log.info("Deleting professor {}", id);

        professorService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
