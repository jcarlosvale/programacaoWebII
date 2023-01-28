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

import com.study.domain.dto.CursoDTO;
import com.study.service.CursoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/cursos")
@RequiredArgsConstructor
@Slf4j
public class CursoController {

	private final CursoService cursoService;

    @GetMapping
    public ResponseEntity<List<CursoDTO>> getAll(){
        log.info("Retornando todos os cursos");
        if(cursoService.getAll().isEmpty()) {
        	return ResponseEntity.ok(List.of());
        } else {
        return ResponseEntity.ok(cursoService.getAll());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoDTO> getById(@PathVariable("id") final int id){
        log.info("Recuperando o curso por id {}", id);
        var aluno = cursoService.getById(id);
        if(Objects.isNull(cursoService)) {
        	return ResponseEntity.notFound().build();
        } else {
        	return ResponseEntity.ok(aluno);
        }
    }

    @PostMapping
    public ResponseEntity<CursoDTO> save(@RequestBody final CursoDTO cursoDTO){
        if(cursoDTO.getNome().trim().equals("")){
        	log.error("Nome do curso inválido {}", cursoDTO.getNome());
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
        	cursoService.save(cursoDTO);
        	log.info("Inserindo um novo curso {}", cursoDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(cursoDTO);
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<CursoDTO> update (@PathVariable("id") final int id, @RequestBody final CursoDTO cursoDTO){
    	log.info("Atualizando curso por id {}", id);
    	cursoService.update(id, cursoDTO);
    	return ResponseEntity.ok(cursoDTO);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete (@PathVariable("id") final int id) throws Exception{
    	log.info("Deletando curso por id {}", id);
        cursoService.delete(id);
       return ResponseEntity.noContent().build();
      }
}
