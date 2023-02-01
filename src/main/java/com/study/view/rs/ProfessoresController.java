package com.study.view.rs;

import com.study.domain.model.Professores;
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
@RequestMapping(path = "/professores")
@RequiredArgsConstructor
@Slf4j
public class ProfessoresController {

    private final ProfessorService service;

    @GetMapping
    public ResponseEntity<List<Professores>> getAll() {
        log.info("Listing professores");

        List<Professores> ListaDeProfessores = service.retrieveAll();
        if (ListaDeProfessores.isEmpty()) {
            return ResponseEntity.ok(List.of());
        } else {
            return ResponseEntity
                    .ok(new ArrayList<>(ListaDeProfessores));
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Professores> getById(@PathVariable("id") final Long id) {
        log.info("Getting professor {}", id);
        var professor = service.getById(id);

            return ResponseEntity
                    .ok(professor);

    }

    @PostMapping
    public ResponseEntity<Professores> save(@RequestBody @Valid final Professores professor) {
        if (service.getById(professor.getId()) != null) {
            log.error("Collection contains id {}", professor.getId());
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .build();
        } else {
            service.save(professor);
            log.info("Inserted a new aluno {}", professor);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(professor);
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Professores> update(@PathVariable("id") final Long id, @RequestBody @Valid final Professores professorDto) {
        log.info("Updating aluno {}", id);
        Professores professor = service.getById(id);

        if (professor == null) {
            return ResponseEntity.notFound().build();
        }

        service.update(id, professorDto);

        return ResponseEntity
                .ok(professor);

    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") final Long id) {
        log.info("Deleting professor {}", id);
        var professor = service.getById(id);

        if (Objects.isNull(professor)) {
            return ResponseEntity
                    .notFound()
                    .build();
        } else {
            service.delete(id);
            return ResponseEntity
                    .noContent()
                    .build();
        }
    }
}
