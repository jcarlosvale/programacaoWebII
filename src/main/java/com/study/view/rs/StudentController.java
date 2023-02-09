package com.study.view.rs;


import com.study.dto.StudentRequestDTO;
import com.study.dto.StudentResponseDTO;
import com.study.dto.TutorResponseDTO;
import com.study.service.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/alunos")
@Slf4j
public class StudentController {
    private final StudentService service;

    @GetMapping
    public ResponseEntity<List<StudentResponseDTO>> list() {
        final var response = service.retrieveAll();

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<StudentResponseDTO> save(@Valid final StudentRequestDTO request) {
        final var response = service.save(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> getById(@PathVariable("id") final Integer id) {
        final var response = service.getById(id);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/tutor/{teacherId}")
    public ResponseEntity<TutorResponseDTO> updateTitular(@PathVariable("id") final Integer studentId, @PathVariable("teacherId") final Integer teacherId) {
        final var response = service.updateTutor(studentId, teacherId);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
