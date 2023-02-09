package com.study.view.rs;

import com.study.dto.SubjectResponseDTO;
import com.study.service.SubjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/disciplinas")
@Slf4j
public class SubjectController {
    private final SubjectService service;

    @GetMapping("/{id}")
    public ResponseEntity<SubjectResponseDTO> getSubject(@PathVariable("id") final Integer id) {
        final SubjectResponseDTO response = service.getById(id);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{subjectId}/titular/{teacherId}")
    public ResponseEntity<SubjectResponseDTO> updateTitularBySubject(@PathVariable("subjectId") final Integer subjectId, @PathVariable("teacherId") final Integer teacherId) {
        final SubjectResponseDTO response = service.updateTitularBySubject(subjectId, teacherId);

        return ResponseEntity.ok(response);
    }
}
