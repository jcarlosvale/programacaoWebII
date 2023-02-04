package com.study.view.rs;

import com.study.dto.response.DisciplinaResponse;
import com.study.service.disciplina.DisciplinaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping(path = "/disciplinas")
@RequiredArgsConstructor
@Slf4j
public class DisciplinaController {

    private final DisciplinaService disciplinaService;

    @PostMapping
    public ResponseEntity<Void> createDisciplina(@RequestBody @Valid final DisciplinaResponse disciplina){
        return disciplinaService.createDisciplina(disciplina);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<DisciplinaResponse> getById(@PathVariable ("id") final int id){
        return disciplinaService.getById(id);
    }

    public ResponseEntity<List<DisciplinaResponse>> getAll(){
        return disciplinaService.getAll();
    }

    @PutMapping(path = "/{îd}")
    public ResponseEntity<DisciplinaResponse> update(@PathVariable("id") final int id, @RequestBody @Valid DisciplinaResponse disciplina) {
        return disciplinaService.update(id, disciplina);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") final int id){
        return disciplinaService.delete(id);
    }

    @GetMapping()
    public ResponseEntity<List<DisciplinaResponse>> getByPrefix(@RequestParam(value = "prefixo", required = false) final String prefixo){
        return disciplinaService.getByPrefix(prefixo);
    }
}
