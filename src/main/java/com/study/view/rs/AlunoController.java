package com.study.view.rs;

import com.study.domain.dto.AlunoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.*;
import java.util.stream.Collectors;

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
    private final Map<Integer, AlunoDto> repository = new HashMap<>();

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody final AlunoDto aluno){
        if(repository.containsKey(aluno.getId())){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }

        repository.put(aluno.getId(), aluno);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<AlunoDto> getById(@PathParam("id") final int id){
        AlunoDto aluno = repository.get(id);

        if(aluno == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        return ResponseEntity.ok(aluno);
    }

    @GetMapping
    public ResponseEntity<List<AlunoDto>> getAll(){
        return ResponseEntity.ok(new ArrayList<>(repository.values()));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<AlunoDto> update(@PathParam("id") final int id,
                                           @RequestBody final AlunoDto alunoNovo){
        if(!repository.containsKey(alunoNovo.getId())){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        repository.put(id, alunoNovo);
        return ResponseEntity
                .ok(alunoNovo);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathParam("id") final int id){
        if(!repository.containsKey(id)){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        repository.remove(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @GetMapping
    public ResponseEntity<List<AlunoDto>> getByPrefix(@RequestParam(value = "prefixo", required = true)
                                                      final String prefixo){
        if(Objects.isNull(prefixo)) return getAll();

        var alunos = repository.values().stream()
                .filter(aluno -> aluno.getNome().startsWith(prefixo))
                .collect(Collectors.toList());

        return ResponseEntity
                .ok(alunos);
    }


}
