package com.study.view.rs;

import com.study.dto.commons.TodoDto;
import com.study.dto.request.AlunoRequest;
import com.study.dto.response.AlunoResponse;
import com.study.dto.response.TutorResponse;
import com.study.service.aluno.AlunoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping(path = "/alunos")
@RequiredArgsConstructor
@Slf4j
public class AlunoController {

    private final AlunoService service;

    @PostMapping
    public ResponseEntity<AlunoResponse> save(@RequestBody @Valid final AlunoRequest aluno){

        AlunoResponse response = service.createAluno(aluno);

       return ResponseEntity
               .status(HttpStatus.CREATED)
               .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponse> getById(@PathVariable ("id") final int id) {
        AlunoResponse response = service.getById(id);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<AlunoResponse>> getAll(){

        List<AlunoResponse> response = service.getAll();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/tutor/{idProfessor}")
    public ResponseEntity<TutorResponse> updateTitular(@PathVariable("id") int idAluno, @PathVariable("idProfessor") int idProfessor){

        TutorResponse response =
                service.updateTutor(idAluno, idProfessor);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping(path="/activity")
    public ResponseEntity<TodoDto> saveRandomTodo() {

        TodoDto dto = service.generateRandomTodo();

        return ResponseEntity.ok(dto);
    }
}
