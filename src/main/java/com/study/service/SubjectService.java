package com.study.service;

import com.study.dto.SubjectRequestDTO;
import com.study.dto.SubjectResponseDTO;
import com.study.mapper.SubjectMapper;
import com.study.model.Subject;
import com.study.repository.SubjectRepository;
import com.study.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class SubjectService {
    private final SubjectMapper mapper;
    private final SubjectRepository repository;
    private final TeacherRepository professorRepository;

    public List<SubjectResponseDTO> retrieveAll() {
        log.info("Listing subject");

        final var listOfEntities = repository.findAll();
        
        return  mapper.toResponse(listOfEntities);
    }

    @Transactional
    public SubjectResponseDTO save(@Valid SubjectRequestDTO request) {
        Objects.requireNonNull(request, "request must not be null");

        log.info("Saving subject - {}", request);

        return mapper.toResponse(repository.save(mapper.toEntity(request)));
    }

    public SubjectResponseDTO getById(Integer id) {
        log.info("Getting subject id - {}", id);

        var subjectOptional = repository.findById(id);
        var entity = subjectOptional.orElseThrow(() -> new EntityNotFoundException("Subject not found"));

        return mapper.toResponse(entity);
    }

    @Transactional
    public SubjectResponseDTO updateTitularBySubject(Integer subjectId, Integer titularId) {
        log.info("Updating titular titular id - {}, subject id - {}", titularId, subjectId);

        // find entities
        var subjectOptional = repository.findById(subjectId);
        var titularOptional = professorRepository.findById(titularId);

        // validate is not empty
        var subject = subjectOptional.orElseThrow(() -> new EntityNotFoundException("Subject not found"));
        var titular = titularOptional.orElseThrow(() -> new EntityNotFoundException("Titular not found"));

        // Update
        subject.setTitular(titular);
        repository.save(subject);

        return mapper.toResponse(subject);
    }

    public List<SubjectResponseDTO> getSubjectByTitular(Integer titularId) {
        log.info("Getting subject by titular id - {}", titularId);

        var titularOptional = professorRepository.findById(titularId);
        var titular = titularOptional.orElseThrow(() -> new EntityNotFoundException("Titular not found"));

        List<Subject> listOfEntities = repository.findSubjectByTitularId(titular);

        return mapper.toResponse(listOfEntities);
    }
}
