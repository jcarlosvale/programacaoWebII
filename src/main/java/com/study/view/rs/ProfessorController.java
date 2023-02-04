package com.study.view.rs;

import com.study.dto.response.ProfessorResponse;
import com.study.service.professor.ProfessorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/professores")
@RequiredArgsConstructor
@Slf4j
public class ProfessorController {

    private final ProfessorService professorService;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid final ProfessorResponse professor){
        return professorService.createProfessor(professor);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProfessorResponse> getById(@PathVariable ("id") final int id){
        return professorService.getById(id);
    }

    public ResponseEntity<List<ProfessorResponse>> getAll(){
        return professorService.getAll();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ProfessorResponse> update(@PathVariable("id") final int id, @RequestBody @Valid ProfessorResponse professor) {
        return professorService.update(id, professor);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") final int id){
        return professorService.delete(id);
    }

    @GetMapping()
    public ResponseEntity<List<ProfessorResponse>> getByPrefix(@RequestParam(value = "prefixo", required = false) final String prefixo){
        return professorService.getByPrefix(prefixo);
    }
}
