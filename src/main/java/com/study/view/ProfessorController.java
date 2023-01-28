package com.study.view;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.domain.dto.ProfessorDto;
import com.study.service.ProfessorService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/professores")
@RequiredArgsConstructor
@Slf4j
public class ProfessorController {

    //private final Map<Integer, ProfessorDto> profRepository;
    private final ProfessorService profService;
    
    @PostMapping(path="/save")
    public ResponseEntity<ProfessorDto> save(@RequestBody final ProfessorDto professor) {
        if (profService.containsKey(professor.getId())) {
            log.error("Coleção contem id {}", aluno.getId());
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .build();
        }
        else {
            log.info(String.valueOf(aluno.getId()));
            repository.put(aluno.getId(), aluno);
            log.info("Inserido novo aluno {}", aluno);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(aluno);
        }
    }

    
}
