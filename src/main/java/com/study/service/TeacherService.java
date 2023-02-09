package com.study.service;

import com.study.dto.TeacherRequestDTO;
import com.study.dto.TeacherResponseDTO;
import com.study.mapper.*;
import com.study.model.*;
import com.study.repository.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.stereotype.*;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeacherService {
    private final TeacherMapper mapper;
    private final TeacherRepository repository;

    public List<TeacherResponseDTO> retrieveAll() {
        log.info("Listing teacher");

        return mapper.toResponse(repository.findAll());
    }

    public TeacherResponseDTO getById(Integer id) {
        log.info("Getting teacher id - {}", id);

        var teacherOptional = repository.findById(id);

        if (teacherOptional.isPresent()) {
            return mapper.toResponse(teacherOptional.get());
        }

        return new TeacherResponseDTO();
    }

    @Transactional
    public TeacherResponseDTO save(TeacherRequestDTO request) {
        Objects.requireNonNull(request, "request must not be null");

        log.info("Saving teacher - {}", request);

        return mapper.toResponse(repository.save(mapper.toEntity(request)));
    }

    @Transactional
    public TeacherResponseDTO update(Integer id, TeacherRequestDTO request) {
        Objects.requireNonNull(request, "request must not be null");

        log.info("Updating teacher id - {}, data - {}", id, request);

        var teacherOptional = repository.findById(id);

        teacherOptional.orElseThrow(() -> new EntityNotFoundException("Teacher not found."));

        Teacher entity = mapper.toEntity(request);
        entity.setId(id);

        repository.save(entity);

        return mapper.toResponse(entity);
    }

    public void delete(int id) {
        log.info("Deleting teacher id - {}", id);

        repository.deleteById(id);
    }

}
