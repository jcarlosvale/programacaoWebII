package com.study.view.rs;

import com.study.domain.dto.DisciplinaDto;
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
    public ResponseEntity<Void> createDisciplina(@RequestBody @Valid final DisciplinaDto disciplina){
        return disciplinaService.createDisciplina(disciplina);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<DisciplinaDto> getById(@PathVariable ("id") final int id){
        return disciplinaService.getById(id);
    }

    public ResponseEntity<List<DisciplinaDto>> getAll(){
        return disciplinaService.getAll();
    }

    @PutMapping(path = "/{îd}")
    public ResponseEntity<DisciplinaDto> update(@PathVariable("id") final int id, @RequestBody @Valid DisciplinaDto disciplina) {
        return disciplinaService.update(id, disciplina);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") final int id){
        return disciplinaService.delete(id);
    }

    @GetMapping()
    public ResponseEntity<List<DisciplinaDto>> getByPrefix(@RequestParam(value = "prefixo", required = false) final String prefixo){
        return disciplinaService.getByPrefix(prefixo);
    }
}
