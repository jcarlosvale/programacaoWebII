package com.study.service;

import com.study.dto.CourseRequestDTO;
import com.study.dto.CourseResponseDTO;
import com.study.mapper.CourseMapper;
import com.study.model.Course;
import com.study.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseMapper mapper;
    private final CourseRepository repository;

    public List<CourseResponseDTO> retrieveAll() {
        log.info("Listing course");
        return mapper.toResponse(repository.findAll());
    }

    public CourseResponseDTO getById(Integer id) {
        log.info("Getting course id - {}", id);
        var courseOptional = repository.findById(id);

        if (courseOptional.isPresent()) {
            return mapper.toResponse(courseOptional.get());
        }

        return new CourseResponseDTO();
    }

    @Transactional
    public CourseResponseDTO save(CourseRequestDTO request) {
        Objects.requireNonNull(request, "request must not be null");

        log.info("Saving course - {}", request);

        return mapper.toResponse(repository.save(mapper.toEntity(request)));
    }

    @Transactional
    public CourseResponseDTO update(Integer id, CourseRequestDTO request) {
        Objects.requireNonNull(request, "request must not be null");

        log.info("Updating course id - {}, data - {}", id, request);

        var courseOptional = repository.findById(id);

        courseOptional.orElseThrow(() -> new EntityNotFoundException("Course not found."));

        Course entity = mapper.toEntity(request);
        entity.setId(id);

        repository.save(entity);

        return mapper.toResponse(entity);
    }

    public void delete(Integer id) {
        log.info("Deleting course id - {}", id);

        repository.deleteById(id);
    }
}