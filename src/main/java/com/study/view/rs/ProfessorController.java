package com.study.view.rs;

import com.study.dto.AlunoResponse;
import com.study.dto.DisciplinaResponse;
import com.study.service.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/professores")
public class ProfessorController {

    private final ProfessorService professorService;

    @GetMapping(path = "/{id}/disciplina")
    public ResponseEntity<DisciplinaResponse> getDisciplina(@PathParam("id") int professorId) {
        DisciplinaResponse response = professorService.getProfessorDisciplina(professorId);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(path = "/{id}/tutorados")
    public ResponseEntity<List<AlunoResponse>> getTutorados(@PathParam("id") int professorId) {
        List<AlunoResponse> tutorados = professorService.getProfessorTutorados(professorId);
        return ResponseEntity.ok().body(tutorados);
    }

}
