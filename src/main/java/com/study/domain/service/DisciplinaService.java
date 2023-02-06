package com.study.domain.service;

import com.study.domain.dto.DisciplinasRequest;
import com.study.domain.dto.DisciplinasResponse;
import com.study.domain.mapper.DisciplinaMapper;
import com.study.domain.model.Disciplinas;
import com.study.domain.repositories.DisciplinaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class DisciplinaService {

    private final DisciplinaRepository repository;
    private final DisciplinaMapper mapper;

    public List<DisciplinasResponse> retrieveAll() {
        log.info("Listing cursos");
        return mapper.toResponse(repository.findAll());
    }

    public DisciplinasResponse getById(Long id) {
        log.info("Getting curso id-{}", id);
        var optionalCurso = repository.findById(id);

        if (optionalCurso.isPresent()) {
            return mapper.toResponse(optionalCurso.get());
        }

        return new DisciplinasResponse();
    }

    public DisciplinasResponse save(DisciplinasRequest request) {
        Objects.requireNonNull(request, "request must not be null");

        log.info("Saving curso - {}", request);

        return mapper.toResponse(repository.save(mapper.toEntity(request)));
    }

    public DisciplinasResponse update(Long id, DisciplinasRequest request) {
        Objects.requireNonNull(request, "request must not be null");

        log.info("Updating curso id - {}, data - {}", id, request);

        var optionalCurso = repository.findById(id);

        optionalCurso.orElseThrow(() -> new EntityNotFoundException("Curso not found."));

        Disciplinas entity = mapper.toEntity(request);
        entity.setId(id);

        repository.save(entity);
        return mapper.toResponse(entity);
    }

    public void delete(Long id) {
        log.info("Deleting curso id - {}", id);
        repository.deleteById(id);
    }
}
