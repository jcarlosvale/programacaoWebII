package com.study.view.rs;

import com.study.domain.dto.AlunoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/alunos")
@RequiredArgsConstructor
@Slf4j
public class AlunoController {
    private final Map<Integer, AlunoDto> repository = new HashMap<>();

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody final AlunoDto aluno){
       if(repository.containsKey(aluno.getId())){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
       }
        repository.put(aluno.getId(), aluno);
       return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<AlunoDto> getById(@PathVariable ("id") final int id){
        AlunoDto aluno = repository.get(id);
        if(Objects.isNull(aluno)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(aluno);
    }

    public ResponseEntity<List<AlunoDto>> getAll(){
        if(repository.isEmpty()){
            return ResponseEntity.ok(List.of());
        }
        return ResponseEntity.ok(new ArrayList<>(repository.values()));
    }

    @PutMapping(path = "/{îd}")
    public ResponseEntity<AlunoDto> update(@PathVariable("id") final int id, @RequestBody AlunoDto aluno) {
        if (!repository.containsKey(aluno.getId())) {
            return ResponseEntity.notFound().build();
        }
        repository.put(id, aluno);
        return ResponseEntity.ok(aluno);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") final int id){
        AlunoDto aluno = repository.get(id);
        if(Objects.isNull(aluno)){
            return ResponseEntity.notFound().build();
        }
        repository.remove(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    public ResponseEntity<List<AlunoDto>> getByPrefix(@RequestParam(value = "prefixo", required = false) final String prefixo){
        if(Objects.isNull(prefixo)) return getAll();
        var alunos = repository.values().stream()
                        .filter(aluno -> aluno.getName().startsWith(prefixo))
                        .collect(Collectors.toList());
        return ResponseEntity.ok(alunos);
    }
}
