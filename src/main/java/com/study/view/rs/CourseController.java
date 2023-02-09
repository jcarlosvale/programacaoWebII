package com.study.view.rs;

import com.study.dto.CourseRequestDTO;
import com.study.dto.CourseResponseDTO;
import com.study.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/v1/cursos")
@RequiredArgsConstructor
@Slf4j
public class CourseController {
    private final CourseService service;

    @GetMapping
    public ResponseEntity<List<CourseResponseDTO>> listCourse() {
        final List<CourseResponseDTO> response = service.retrieveAll();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> getCourse(@PathVariable("id") final Integer id) {
        final CourseResponseDTO response = service.getById(id);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CourseResponseDTO> saveCourse(@RequestBody @Valid final CourseRequestDTO course) {
        var response = service.save(course);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> updateCourse(@PathVariable("id") final Integer id, @RequestBody @Valid final CourseRequestDTO course) {
        var response = service.update(id, course);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeCourse(@PathVariable("id") final Integer id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}