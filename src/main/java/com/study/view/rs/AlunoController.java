package com.study.view.rs;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.dto.request.AlunoRequest;
import com.study.dto.response.AlunoResponse;
import com.study.dto.response.TutorResponse;
import com.study.service.AlunoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/alunos")
@RequiredArgsConstructor
@Slf4j
public class AlunoController {

    private final AlunoService alunoService;

    @PostMapping
    public ResponseEntity<AlunoResponse> save(@RequestBody @Valid final AlunoRequest aluno){

        AlunoResponse response = alunoService.createAluno(aluno);

       return ResponseEntity
               .status(HttpStatus.CREATED)
               .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponse> getById(@PathVariable ("id") final int id) {
        AlunoResponse response = alunoService.getById(id);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<AlunoResponse>> getAll(){

        List<AlunoResponse> response = alunoService.getAll();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/tutor/{idProfessor}")
    public ResponseEntity<TutorResponse> updateTitular(@PathVariable("id") int idAluno, @PathVariable("idProfessor") int idProfessor){

        TutorResponse response =
                alunoService.updateTutor(idAluno, idProfessor);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}