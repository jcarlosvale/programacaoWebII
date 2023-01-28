package com.study.view.rs;

import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.domain.dto.ProfessorDTO;
import com.study.service.ProfessorService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/professores")
@RequiredArgsConstructor
@Slf4j
public class ProfessorController {

	private final ProfessorService professorService;

    @GetMapping
    public ResponseEntity<List<ProfessorDTO>> getAll(){
        log.info("Retornando todos os professores");
        if(professorService.getAll().isEmpty()) {
        	return ResponseEntity.ok(List.of());
        } else {
        return ResponseEntity.ok(professorService.getAll());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorDTO> getById(@PathVariable("id") final int id){
        log.info("Recuperando o professor por id {}", id);
        var aluno = professorService.getById(id);
        if(Objects.isNull(professorService)) {
        	return ResponseEntity.notFound().build();
        } else {
        	return ResponseEntity.ok(aluno);
        }
    	
    }

    @PostMapping
    public ResponseEntity<ProfessorDTO> save(@RequestBody final ProfessorDTO professorDTO){
   	        
        if(professorDTO.getNome().trim().equals("")){
        	log.error("Nome do professor inválido {}", professorDTO.getNome());
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
        	professorService.save(professorDTO);
        	
        	log.info("Inserindo um novo professor {}", professorDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(professorDTO);
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ProfessorDTO> update (@PathVariable("id") final int id, @RequestBody final ProfessorDTO professorDTO){
    	log.info("Atualizando professor por id {}", id);
    	professorService.update(id, professorDTO);
    	return ResponseEntity.ok(professorDTO);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete (@PathVariable("id") final int id){
    	log.info("Deletando professor por id {}", id);
        var curso = professorService.getById(id);
        if (Objects.isNull(curso)) {
            return ResponseEntity
                    .notFound()
                    .build();
        }
        else {
            log.info("Deletando professor por id {}", id);
            professorService.delete(id);
            return ResponseEntity
                    .noContent()
                    .build();
        }
    }
}
