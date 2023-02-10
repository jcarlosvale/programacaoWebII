package com.study.view.rs;

import com.study.dto.request.DisciplinaRequest;
import com.study.dto.response.DisciplinaResponse;
import com.study.dto.response.TitularResponse;
import com.study.service.disciplina.DisciplinaService;
import com.study.service.professor.ProfessorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping(path = "/disciplinas")
@RequiredArgsConstructor
@Slf4j
public class DisciplinaController {

    private final DisciplinaService service;

    @PostMapping
    public ResponseEntity<DisciplinaResponse> createDisciplina(@RequestBody @Valid final DisciplinaRequest request){
        DisciplinaResponse response = service.createDisciplina(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisciplinaResponse> getById(@PathVariable ("id") final int id){
        DisciplinaResponse response = service.getById(id);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<List<DisciplinaResponse>> getAll(){
        List<DisciplinaResponse> response = service.getAll();
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/titular/{idProfessor}")
    public ResponseEntity<TitularResponse> updateTitular(@PathVariable("id") int id, @PathVariable("idProfessor") int idProfessor) {
        final TitularResponse response = service.update(id, idProfessor);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") final int id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
