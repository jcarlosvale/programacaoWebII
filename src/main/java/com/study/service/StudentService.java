package com.study.service;


import com.study.dto.StudentRequestDTO;
import com.study.dto.StudentResponseDTO;
import com.study.dto.TutorResponseDTO;
import com.study.mapper.*;
import com.study.model.*;
import com.study.repository.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.stereotype.*;

import javax.persistence.*;
import javax.transaction.*;
import javax.validation.*;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentService {
    private final StudentMapper mapper;
    private final StudentRepository repository;
    private final TeacherRepository teacherRepository;

    public List<StudentResponseDTO> retrieveAll() {
        log.info("Listing student");

        final var listOfEntities = repository.findAll();

        return  mapper.toResponse(listOfEntities);
    }

    @Transactional
    public StudentResponseDTO save(@Valid StudentRequestDTO request) {
        Objects.requireNonNull(request, "request must not be null");

        log.info("Saving student - {}", request);

        var entity =
                Student.builder()
                        .name(request.getName())
                        .build();

        repository.save(entity);

        return mapper.toResponse(entity);
    }

    public StudentResponseDTO getById(Integer id) {
        log.info("Getting student id - {}", id);

        var studentOptional = repository.findById(id);
        var entity = studentOptional.orElseThrow(() -> new EntityNotFoundException("Student not found"));

        return mapper.toResponse(entity);
    }

    @Transactional
    public TutorResponseDTO updateTutor(Integer studentId, Integer teacherId) {
        log.info("Updating tutor student-id - {}, teacher-id - {}", studentId, teacherId);

        // find entities
        var studentOptional = repository.findById(studentId);
        var teacherOptional = teacherRepository.findById(teacherId);

        // validate is not empty
        var student = studentOptional.orElseThrow(() -> new EntityNotFoundException("Student not found"));
        var teacher = teacherOptional.orElseThrow(() -> new EntityNotFoundException("Teacher not found"));

        // Update
        student.setTutor(teacher);
        repository.save(student);

        return mapper.toResponse(teacher);
    }

    public List<StudentResponseDTO> getStudentByTutorId(Integer teacherId) {
        log.info("Getting students by teacher id - {}", teacherId);

        var teacherOptional = teacherRepository.findById(teacherId);
        var teacher = teacherOptional.orElseThrow(() -> new EntityNotFoundException("Teacher not found"));

        List<Student> listOfEntities = repository.findStudentByTutor(teacher);

        return mapper.toResponse(listOfEntities);
    }
}
