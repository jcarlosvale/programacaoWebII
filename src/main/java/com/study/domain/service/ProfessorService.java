package com.study.domain.service;

import com.study.domain.dto.AlunosRequest;
import com.study.domain.dto.ProfessoresRequest;
import com.study.domain.mapper.ProfessorMapper;
import com.study.domain.model.Professores;
import com.study.domain.repositories.ProfessoresRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessoresRepository repository;

    private final ProfessorMapper mapper;

    public List<Professores> retrieveAll() {
        log.info("Listing professores");
        return repository.findAll();
    }

    public Professores getById(Long id) {
        log.info("Getting professor id-{}", id);

        return repository.getReferenceById(id);
    }

    public void save(Professores professor) {
        log.info("Saving professor - {}", professor);

        repository.save(professor);
    }

    public void update(Long id, ProfessoresRequest professor) {
        log.info("Updating professor id - {}, data - {}", id, professor);

        repository.save(mapper.toEntity(professor));
    }

    public void delete(Long id) {
        log.info("Deleting professor id - {}", id);
        repository.delete(repository.getReferenceById(id));
    }
}
