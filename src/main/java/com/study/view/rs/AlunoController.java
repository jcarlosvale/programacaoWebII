package com.study.view.rs;

import com.study.dto.AlunoRequest;
import com.study.dto.AlunoResponse;
import com.study.dto.TodoDto;
import com.study.dto.TutorResponse;
import com.study.service.AlunoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.*;

/*
* Projeto criado pelos alunos:
* Davi Almeida
* Luiz Gustavo Cantrella
* Rodolfo Araujo
* */
@RestController
@RequestMapping(path = "/alunos")
@RequiredArgsConstructor
@Slf4j
public class AlunoController {
    private final AlunoService service;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid final AlunoRequest aluno){
        service.save(aluno);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<AlunoResponse> getById(@PathParam("id") final int id){
        AlunoResponse aluno = service.getById(id);
        return ResponseEntity.ok(aluno);
    }

    @GetMapping
    public ResponseEntity<List<AlunoResponse>> getAll(){
        return ResponseEntity.ok(service.retrieveAll());
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<AlunoResponse> update(@PathParam("id") final int id,
                                                   @RequestBody @Valid final AlunoRequest alunoNovo){
        AlunoResponse response = service.update(id, alunoNovo);
        return ResponseEntity
                .ok(response);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathParam("id") final int id){
        service.delete(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @PatchMapping(path = "/{id}/tutor/{id-tutor}")
    public ResponseEntity<TutorResponse> updateTutor(
            @PathParam("id") int alunoId, @PathParam("id-tutor") int tutorId) {
        TutorResponse response = service.updateTutor(alunoId, tutorId);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(path="/activity")
    public ResponseEntity<TodoDto> saveRandomTodo() {
        final TodoDto dto = service.generateRandomTodo();

        return ResponseEntity.ok(dto);
    }

    @GetMapping(params= "prefixo")
    public ResponseEntity<List<AlunoResponse>> getByPrefix(@RequestParam(value = "prefixo", required = true)
                                                      final String prefixo) {
        if (Objects.isNull(prefixo)) return getAll();
//
//        var alunos = repository.values().stream()
//                .filter(aluno -> aluno.getNome().startsWith(prefixo))
//                .collect(Collectors.toList());

        return ResponseEntity
                .ok(List.of(new AlunoResponse()));
    }

}
