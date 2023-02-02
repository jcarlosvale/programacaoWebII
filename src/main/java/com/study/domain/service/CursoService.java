package com.study.domain.service;

import com.study.domain.dto.CursosRequest;
import com.study.domain.dto.CursosResponse;
import com.study.domain.mapper.CursoMapper;
import com.study.domain.model.Cursos;
import com.study.domain.repositories.CursosRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursosRepository repository;
    private final CursoMapper mapper;

    public List<CursosResponse> retrieveAll() {
        log.info("Listing cursos");
        return mapper.toResponse(repository.findAll());
    }

    public CursosResponse getById(Long id) {
        log.info("Getting curso id-{}", id);
        var optionalCurso = repository.findById(id);

        if (optionalCurso.isPresent()) {
            return mapper.toResponse(optionalCurso.get());
        }

        return new CursosResponse();
    }

    public CursosResponse save(CursosRequest request) {
        Objects.requireNonNull(request, "request must not be null");

        log.info("Saving curso - {}", request);

        return mapper.toResponse(repository.save(mapper.toEntity(request)));
    }

    public CursosResponse update(Long id, CursosRequest request) {
        Objects.requireNonNull(request, "request must not be null");

        log.info("Updating curso id - {}, data - {}", id, request);

        var optionalCurso = repository.findById(id);

        optionalCurso.orElseThrow(() -> new EntityNotFoundException("Curso not found."));

        Cursos entity = mapper.toEntity(request);
        entity.setId(id);

        repository.save(entity);
        return mapper.toResponse(entity);
    }

    public void delete(Long id) {
        log.info("Deleting curso id - {}", id);
        repository.deleteById(id);
    }
}
