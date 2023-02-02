package com.study.view.rs;

import com.study.domain.dto.AlunosRequest;
import com.study.domain.dto.AlunosResponse;
import com.study.domain.service.AlunoService;
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
public class AlunosController {

    private final AlunoService service;

    @GetMapping
    public ResponseEntity<List<AlunosResponse>> listAlunos() {
        final List<AlunosResponse> alunoDtoList = service.retrieveAll();
        return ResponseEntity.ok(alunoDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunosResponse> getAluno(@PathVariable("id") Long id) {
        final AlunosResponse alunoDto = service.getById(id);
        return ResponseEntity.ok(alunoDto);
    }

    @PostMapping
    public ResponseEntity<AlunosResponse> saveAluno(@RequestBody @Valid final AlunosRequest aluno) {
        var response = service.save(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunosResponse> updateAluno(@PathVariable("id") Long id,
                                                      @RequestBody @Valid AlunosRequest aluno) {

        var response = service.update(id, aluno);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeAluno(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
