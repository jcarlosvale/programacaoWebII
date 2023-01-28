package com.study.view.rs;

import com.study.domain.dto.ProfessorDto;
import com.study.service.professor.ProfessorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/professores")
@RequiredArgsConstructor
@Slf4j
public class ProfessorController {

    private final ProfessorService professorService;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody final ProfessorDto professor){
        return professorService.createProfessor(professor);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProfessorDto> getById(@PathVariable ("id") final int id){
        return professorService.getById(id);
    }

    public ResponseEntity<List<ProfessorDto>> getAll(){
        return professorService.getAll();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ProfessorDto> update(@PathVariable("id") final int id, @RequestBody ProfessorDto professor) {
        return professorService.update(id, professor);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") final int id){
        return professorService.delete(id);
    }

    @GetMapping()
    public ResponseEntity<List<ProfessorDto>> getByPrefix(@RequestParam(value = "prefixo", required = false) final String prefixo){
        return professorService.getByPrefix(prefixo);
    }
}
