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

}
