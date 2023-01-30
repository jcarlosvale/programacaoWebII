package com.study.view.rs;

import com.study.domain.dto.AlunoDto;
import com.study.service.aluno.AlunoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping(path = "/alunos")
@RequiredArgsConstructor
@Slf4j
public class AlunoController {

    private final AlunoService alunoService;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid final AlunoDto aluno){
       return alunoService.createAluno(aluno);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<AlunoDto> getById(@PathVariable ("id") final int id){
        return alunoService.getById(id);
    }

    public ResponseEntity<List<AlunoDto>> getAll(){
        return alunoService.getAll();
    }

    @PutMapping(path = "/{îd}")
    public ResponseEntity<AlunoDto> update(@PathVariable("id") final int id, @RequestBody @Valid AlunoDto aluno) {
      return alunoService.update(id, aluno);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") final int id){
        return alunoService.delete(id);
    }

    @GetMapping()
    public ResponseEntity<List<AlunoDto>> getByPrefix(@RequestParam(value = "prefixo", required = false) final String prefixo){
        return alunoService.getByPrefix(prefixo);
    }
}
