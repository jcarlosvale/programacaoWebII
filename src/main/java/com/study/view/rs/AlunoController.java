package com.study.view.rs;

import com.study.dto.AlunoDto;
import com.study.service.AlunoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v3/alunos")
@Slf4j
public class AlunoController {

    private final AlunoService service;

    @Autowired
    public AlunoController(AlunoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<AlunoDto>> listarAlunos() {

        final List<AlunoDto> alunoDtoList = service.retrieveAll();

        return ResponseEntity.ok(alunoDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoDto> getalunos(@PathVariable("id") int id) {

        final AlunoDto alunoDto = service.getById(id);

        return ResponseEntity.ok(alunoDto);
    }

    @PostMapping
    public ResponseEntity<Void> savePAluno(@RequestBody @Valid final AlunoDto aluno) {

        service.save(aluno);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoDto> updateAluno(@PathVariable("id") int id, @RequestBody @Valid AlunoDto aluno) {

        service.update(id, aluno);

        return ResponseEntity
                .ok(aluno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removealuno(@PathVariable("id") int id) {

        service.delete(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}
