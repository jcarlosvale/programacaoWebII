package com.study.view.rs;

import com.study.dto.AlunoRequestDto;
import com.study.dto.AlunoResponseDto;
import com.study.service.AlunoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.*;

/*
* Projeto criado pelos alunos:
* Davi Almeida
* Luiz Gustavo Cantrella
* Rodolfo Araujo
* */
@RestController
@RequestMapping(path = "/alunos")
@RequiredArgsConstructor
@Slf4j
public class AlunoController {
    private final AlunoService service;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid final AlunoRequestDto aluno){
        service.save(aluno);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<AlunoResponseDto> getById(@PathParam("id") final int id){
        AlunoResponseDto aluno = service.getById(id);
        return ResponseEntity.ok(aluno);
    }

    @GetMapping
    public ResponseEntity<List<AlunoResponseDto>> getAll(){
        return ResponseEntity.ok(service.retrieveAll());
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<AlunoResponseDto> update(@PathParam("id") final int id,
                                                   @RequestBody @Valid final AlunoRequestDto alunoNovo){
        AlunoResponseDto response = service.update(id, alunoNovo);
        return ResponseEntity
                .ok(response);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathParam("id") final int id){
        service.delete(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    //@GetMapping
    /*
    public ResponseEntity<List<AlunoDto>> getByPrefix(@RequestParam(value = "prefixo", required = true)
                                                      final String prefixo){
        if(Objects.isNull(prefixo)) return getAll();

        var alunos = repository.values().stream()
                .filter(aluno -> aluno.getNome().startsWith(prefixo))
                .collect(Collectors.toList());

        return ResponseEntity
                .ok(alunos);
    }
     */


}
