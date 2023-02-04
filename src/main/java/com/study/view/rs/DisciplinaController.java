package com.study.view.rs;

import com.study.dto.DisciplinaResponse;
import com.study.service.DisciplinaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/disciplinas")
@RequiredArgsConstructor
public class DisciplinaController {

    private final DisciplinaService disciplinaService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<DisciplinaResponse> getDisciplina(@PathParam("id") int id) {
        return ResponseEntity.ok().body(disciplinaService.getById(id));
    }

    @PatchMapping(path = "/{id}/titular/{id-professor}")
    public ResponseEntity<DisciplinaResponse> updateTitular(
            @PathParam("id") int disciplinaId, @PathParam("id-professor") int professorId) {
        DisciplinaResponse response = disciplinaService.updateTitular(disciplinaId, professorId);
        return ResponseEntity.ok().body(response);
    }
}
