package com.study.view.rs;

import com.study.dto.request.ProfessorRequest;
import com.study.dto.response.AlunoResponse;
import com.study.dto.response.DisciplinaResponse;
import com.study.dto.response.ProfessorResponse;
import com.study.service.aluno.AlunoService;
import com.study.service.disciplina.DisciplinaService;
import com.study.service.professor.ProfessorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/professores")
@RequiredArgsConstructor
@Slf4j
public class ProfessorController {

    private final ProfessorService service;

    private final AlunoService alunoService;

    private final DisciplinaService disciplinaService;

    @PostMapping
    public ResponseEntity<ProfessorResponse> save(@RequestBody @Valid final ProfessorRequest professor) {
        ProfessorResponse response = service.createProfessor(professor);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorResponse> getById(@PathVariable ("id") final int id){
        ProfessorResponse response = service.getById(id);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ProfessorResponse>> getAll(){
        List<ProfessorResponse> responseList = service.getAll();

        return ResponseEntity.ok(responseList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessorResponse> update(@PathVariable("id") final int id, @RequestBody @Valid ProfessorRequest request) {
        ProfessorResponse response = service.update(id, request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") final int id){
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/tutorados")
    public  ResponseEntity<List<AlunoResponse>> tutorados(@PathVariable("id") final int id) {
        List<AlunoResponse> response = alunoService.getTutoradosByProfessorId(id);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/disciplina")
    public ResponseEntity<DisciplinaResponse> getDisciplina(@PathVariable("id") int id) {
        DisciplinaResponse response =
                disciplinaService.getDisciplinaByProfessorId(id);

        return ResponseEntity.ok(response);
    }

}
