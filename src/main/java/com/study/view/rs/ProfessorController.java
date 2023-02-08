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

import com.study.dto.request.ProfessorRequest;
import com.study.dto.response.AlunoResponse;
import com.study.dto.response.ProfessorResponse;
import com.study.service.AlunoService;
import com.study.service.ProfessorService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/professores")
@RequiredArgsConstructor
@Slf4j
public class ProfessorController {

    private final ProfessorService service;

    private final AlunoService alunoService;

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

}