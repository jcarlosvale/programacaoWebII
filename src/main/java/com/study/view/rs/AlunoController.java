package com.study.view.rs;

import com.study.domain.dto.AlunoRequestDto;
import com.study.domain.dto.AlunoResponseDto;
import com.study.service.AlunoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/alunos")
@RequiredArgsConstructor
@Slf4j
public class AlunoController {

    private final AlunoService alunoService;

    @PostMapping
    public ResponseEntity<AlunoResponseDto> save(@RequestBody @Valid final AlunoRequestDto aluno) {

            AlunoResponseDto alunoCreated =  alunoService.save(aluno);
            log.info("Inserted a new aluno {}", aluno);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(alunoCreated);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<AlunoResponseDto> getById(@PathVariable("id") final int id) {
        log.info("Getting aluno {}", id);
        var aluno = alunoService.getById(id);
            return ResponseEntity
                    .ok(aluno);
    }

    @GetMapping
    public ResponseEntity<List<AlunoResponseDto>> getAll() {
        log.info("Listing alunos");

            return ResponseEntity
                    .ok(alunoService.getAll());
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<AlunoResponseDto> update(@PathVariable("id") final int id, @RequestBody @Valid final AlunoRequestDto aluno) {
        log.info("Updating aluno {}", id);

            AlunoResponseDto alunoReponse =  alunoService.update(id, aluno);
            return ResponseEntity
                    .ok(alunoReponse);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") final int id) {
        log.info("Deleting aluno {}", id);

            alunoService.delete(id);
            return ResponseEntity
                    .noContent()
                    .build();
    }
/*
    @GetMapping
    public ResponseEntity<List<AlunoDto>> getByPrefix(@RequestParam(value = "prefixo", required = false) final String prefixo) {
        log.info("Getting aluno with prefix {}", prefixo);
        if(Objects.isNull(prefixo)) return getAll();
        var selectedAlunos =
                repository.values().stream()
                        .filter(aluno -> aluno.getNome().startsWith(prefixo))
                        .collect(Collectors.toList());
        return ResponseEntity
                .ok(selectedAlunos);
    }
 */
}
