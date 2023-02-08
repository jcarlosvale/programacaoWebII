package com.study.view.rs;

import java.util.List;

import javax.validation.Valid;

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

import com.study.dto.request.DisciplinaRequest;
import com.study.dto.response.DisciplinaResponse;
import com.study.service.DisciplinaService;
import com.study.service.ProfessorService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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