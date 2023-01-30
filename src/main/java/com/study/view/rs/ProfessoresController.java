package com.study.view.rs;

import com.study.domain.dto.AlunosDto;
import com.study.domain.dto.ProfessoresDto;
import com.study.domain.service.AlunoService;
import com.study.domain.service.ProfessorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = "/alunos")
@RequiredArgsConstructor
@Slf4j
public class ProfessoresController {

    private final ProfessorService service;


    //@GetMapping  comentado para evitar conflito de endpoint com request param abaixo
    public ResponseEntity<List<ProfessoresDto>> getAll() {
        log.info("Listing professores");

        List<ProfessoresDto> ListaDeProfessores = service.retrieveAll();
        if (ListaDeProfessores.isEmpty()) {
            return ResponseEntity.ok(List.of());
        }
        else {
            return ResponseEntity
                    .ok(new ArrayList<>(ListaDeProfessores));
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProfessoresDto> getById(@PathVariable("id") final int id) {
        log.info("Getting professor {}", id);
        var professor = service.getById(id);
        if (Objects.isNull(professor)) {
            return ResponseEntity
                    .notFound()
                    .build();
        }
        else {
            return ResponseEntity
                    .ok(professor);
        }
    }

    @PostMapping
    public ResponseEntity<ProfessoresDto> save(@RequestBody @Valid final ProfessoresDto professor) {
        if (service.getById(professor.getId()) != null) {
            log.error("Collection contains id {}", professor.getId());
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .build();
        }
        else {
            service.save(professor);
            log.info("Inserted a new aluno {}", professor);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(professor);
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ProfessoresDto> update(@PathVariable("id") final int id, @RequestBody final ProfessoresDto professorDto) {
        log.info("Updating aluno {}", id);
        ProfessoresDto professor = service.getById(id);

        if (professor == null) {
            return ResponseEntity.notFound().build();
        }

        service.update(id, professorDto);

        return ResponseEntity
                    .ok(professor);

    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") final int id) {
        log.info("Deleting professor {}", id);
        var professor = service.getById(id);

        if (Objects.isNull(professor)) {
            return ResponseEntity
                    .notFound()
                    .build();
        }
        else {
            service.delete(id);
            return ResponseEntity
                    .noContent()
                    .build();
        }
    }
}
