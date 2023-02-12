package com.study.controller;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.study.dto.DisciplinaResponse;
import com.study.service.DisciplinaService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/disciplinas")
public class DisciplinaController {

    @Autowired
    private final DisciplinaService disciplinaService;

    @PostMapping(path = "/save")
    public ResponseEntity<DisciplinaResponse> save(@RequestBody @Valid final DisciplinaResponse disciplina){
        var response = disciplinaService.save(disciplina);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(path = "/")
    public ResponseEntity<Collection<DisciplinaResponse>> getAll(){
        return ResponseEntity.ok(new ArrayList<DisciplinaResponse>(disciplinaService.getAll()));
    }

    @PatchMapping(path = "/savetitular/{id_disciplina}/{id_titular}")
    public DisciplinaResponse updateTitular(@PathVariable @Valid final Integer id_disciplina, @PathVariable @Valid final Integer id_titular){
        return disciplinaService.updateTitular(id_disciplina, id_titular);
    }
}
