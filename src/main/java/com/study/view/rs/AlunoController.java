package com.study.view.rs;

import com.study.dto.AlunoRequestDto;
import com.study.dto.AlunoResponseDto;
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
    public ResponseEntity<List<AlunoResponseDto>> listarAlunos() {

        final List<AlunoResponseDto> alunoDtoList = service.retrieveAll();

        return ResponseEntity.ok(alunoDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponseDto> getalunos(@PathVariable("id") int id) {

        final AlunoResponseDto alunoDto = service.getById(id);

        return ResponseEntity.ok(alunoDto);
    }

    @PostMapping
    public ResponseEntity<Void> saveAluno(@RequestBody @Valid final AlunoRequestDto aluno) {

        service.save(aluno);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoRequestDto> updateAluno(@PathVariable("id") int id, @RequestBody @Valid AlunoRequestDto aluno) {

        service.update(id, aluno);

        return ResponseEntity
                .ok(aluno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removealuno(@PathVariable("id") int id) {

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
