package com.study.view.rs;

import com.study.dto.StudentResponseDTO;
import com.study.dto.SubjectResponseDTO;
import com.study.dto.TeacherRequestDTO;
import com.study.dto.TeacherResponseDTO;
import com.study.service.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.http.*;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.*;
import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/professores")
@Slf4j
public class TeacherController {
    private final TeacherService service;
    private final StudentService studentService;
    private final SubjectService subjectService;

    @GetMapping
    @Secured({"ROLE_PROFESSOR"})
    public ResponseEntity<List<TeacherResponseDTO>> listTeacher() {
        final List<TeacherResponseDTO> response = service.retrieveAll();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherResponseDTO> getTeacher(@PathVariable("id") final Integer id) {
        final TeacherResponseDTO response = service.getById(id);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<TeacherResponseDTO> saveTeacher(@RequestBody @Valid final TeacherRequestDTO teacher) {
        var response = service.save(teacher);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherResponseDTO> updateTeacher(@PathVariable("id") final Integer id, @RequestBody @Valid final TeacherRequestDTO teacher) {
        var response = service.update(id, teacher);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeTeacher(@PathVariable("id") final Integer id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/alunos")
    public ResponseEntity<List<StudentResponseDTO>> getStudentByTeacherId(@PathVariable("id") final Integer id) {
        final var response = studentService.getStudentByTutorId(id);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/disciplina")
    public ResponseEntity<List<SubjectResponseDTO>> getSubjectByTitular(@PathVariable("id") final Integer id) {
        final var response = subjectService.getSubjectByTitular(id);

        return ResponseEntity.ok(response);
    }
}
