package com.study.domain.service;

import com.study.domain.dto.CursosRequest;
import com.study.domain.mapper.CursoMapper;
import com.study.domain.model.Cursos;
import com.study.domain.repositories.CursosRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursosRepository repository;

    private final CursoMapper mapper;

    public List<Cursos> retrieveAll() {
        log.info("Listing cursos");
        return repository.findAll();
    }

    public Cursos getById(Long id) {
        log.info("Getting curso id-{}", id);

        return repository.getReferenceById(id);
    }

    public void save(Cursos curso) {
        log.info("Saving curso - {}", curso);

        repository.save(curso);
    }

    public void update(Long id, CursosRequest curso) {
        log.info("Updating curso id - {}, data - {}", id, curso);
        repository.save(mapper.toEntity(curso));
    }

    public void delete(Long id) {
        log.info("Deleting curso id - {}", id);

        repository.delete(repository.getReferenceById(id));
    }
}
