package com.study.view.rs;

import java.util.List;
import java.util.Objects;

import com.study.domain.dto.response.AlunoResponseDTO;
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

import com.study.domain.dto.AlunoDTO;
import com.study.service.AlunoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/alunos")
@RequiredArgsConstructor
@Slf4j
public class AlunoController {

	private final AlunoService alunoService;

    @GetMapping
    public ResponseEntity<List<AlunoResponseDTO>> getAll(){
        log.info("Retornando todos os alunos");
        if(alunoService.getAll().isEmpty()) {
        	return ResponseEntity.ok(List.of());
        } else {
        	return ResponseEntity.ok(alunoService.getAll());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponseDTO> getById(@PathVariable("id") final int id){
        log.info("Recuperando aluno por id {}", id);
        var aluno = alunoService.getById(id);
        if(Objects.isNull(aluno)) {
        	return ResponseEntity.notFound().build();
        } else {
        	return ResponseEntity.ok(aluno);
        }
    	
    }

    @PostMapping
    public ResponseEntity<AlunoDTO> save(@RequestBody @Valid final AlunoDTO alunoDTO){
        if(alunoDTO.getNome().trim().equals("")){
        	log.error("Nome inválido {}", alunoDTO.getNome());
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
        	alunoService.save(alunoDTO);
        	
        	log.info("Inserindo um novo aluno {}", alunoDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(alunoDTO);
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<AlunoDTO> update (@PathVariable("id") final int id, @RequestBody @Valid final AlunoDTO alunoDTO){
    	log.info("Atualizando aluno por id {}", id);
    	alunoService.update(id, alunoDTO);
    	return ResponseEntity.ok(alunoDTO);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete (@PathVariable("id") final int id) throws Exception{
    	log.info("Deletando aluno por id {}", id);
        alunoService.delete(id);
       return ResponseEntity.noContent().build();
      }
}
