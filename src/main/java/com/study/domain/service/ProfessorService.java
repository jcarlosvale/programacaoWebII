package com.study.domain.service;

import com.study.domain.dto.ProfessoresRequest;
import com.study.domain.dto.ProfessoresResponse;
import com.study.domain.mapper.ProfessorMapper;
import com.study.domain.model.Professores;
import com.study.domain.repositories.ProfessoresRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessoresRepository repository;
    private final ProfessorMapper mapper;

    public List<ProfessoresResponse> retrieveAll() {
        log.info("Listing professores");
        return mapper.toResponse(repository.findAll());
    }

    public ProfessoresResponse getById(Long id) {
        log.info("Getting professor id-{}", id);
        var optionalProfessor = repository.findById(id);

        if (optionalProfessor.isPresent()) {
            return mapper.toResponse(optionalProfessor.get());
        }

        return new ProfessoresResponse();
    }

    public ProfessoresResponse save(ProfessoresRequest request) {
        Objects.requireNonNull(request, "request must not be null");

        log.info("Saving professor - {}", request);

        return mapper.toResponse(repository.save(mapper.toEntity(request)));
    }

    public ProfessoresResponse update(Long id, ProfessoresRequest request) {
        Objects.requireNonNull(request, "request must not be null");

        log.info("Updating professor id - {}, data - {}", id, request);

        var optionalProfessor = repository.findById(id);

        optionalProfessor.orElseThrow(() -> new EntityNotFoundException("Professor not found."));

        Professores entity = mapper.toEntity(request);
        entity.setId(id);

        repository.save(entity);
        return mapper.toResponse(entity);
    }

    public void delete(Long id) {
        log.info("Deleting professor id - {}", id);
        repository.deleteById(id);
    }
}
