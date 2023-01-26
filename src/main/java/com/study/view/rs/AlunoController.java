package com.study.view.rs;

import com.study.domain.dto.AlunoDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "/alunos")
@RequiredArgsConstructor
@Slf4j
public class AlunoController {

    private final Map<Integer,AlunoDTO> repository;

    @GetMapping
    public ResponseEntity<List<AlunoDTO>> getAll(){
        log.info("Retornando todos os alunos");
        return ResponseEntity.ok(new ArrayList<>(repository.values()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoDTO> getById(@PathVariable("id") final int id){
        AlunoDTO aluno = repository.get(id);

        if(!repository.containsKey(aluno.getId())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            log.info("Retornando aluno {}", id);
            return ResponseEntity.ok(aluno);
        }
    }

    @PostMapping
    public ResponseEntity<AlunoDTO> save(@RequestBody final AlunoDTO alunoDTO){
        log.info("Salvando aluno {}", alunoDTO);

        if(repository.containsKey(alunoDTO.getId())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            repository.put(alunoDTO.getId(),alunoDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<AlunoDTO> update (@PathVariable("id") final int id, @RequestBody final AlunoDTO alunoDTO){

        if (!repository.containsKey(alunoDTO.getId())) {
            return ResponseEntity
                    .notFound()
                    .build();
        }
        else {
            log.info("Updating aluno {}", id);
            repository.put(id, alunoDTO);
            return ResponseEntity
                    .ok(alunoDTO);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete (@PathVariable("id") final int id){

        var aluno = repository.get(id);
        if (Objects.isNull(aluno)) {
            return ResponseEntity
                    .notFound()
                    .build();
        }
        else {
            log.info("Deleting aluno {}", id);
            repository.remove(id);
            return ResponseEntity
                    .noContent()
                    .build();
        }
    }
}
