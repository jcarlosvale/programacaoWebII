package com.study.view.rs;

import com.study.dto.request.DisciplinaRequest;
import com.study.dto.response.DisciplinaResponse;
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
    private final ProfessorService professorService;

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

    @PutMapping("/{îd}")
    public ResponseEntity<DisciplinaResponse> update(@PathVariable("id") final int id, @RequestBody @Valid DisciplinaRequest request) {
        DisciplinaResponse response = service.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") final int id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
