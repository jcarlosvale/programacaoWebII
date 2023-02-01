package com.study.domain.service;

import com.study.domain.dto.AlunosRequest;
import com.study.domain.mapper.AlunoMapper;
import com.study.domain.model.Alunos;
import com.study.domain.repositories.AlunosRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunosRepository repository;

    private final AlunoMapper mapper;

    public List<Alunos> retrieveAll() {
        log.info("Listing alunos");
        return repository.findAll();
    }

    public Alunos getById(Long id) {
        log.info("Getting aluno id-{}", id);
        return repository.getReferenceById(id);
    }

    public void save(Alunos aluno) {
        log.info("Saving aluno - {}", aluno);

        repository.save(aluno);
    }

    public void update(Long id, AlunosRequest aluno) {
        log.info("Updating aluno id - {}, data - {}", id, aluno);

        repository.save(mapper.toEntity(aluno));

    }

    public void delete(Long id) {
        log.info("Deleting aluno id - {}", id);
        repository.delete(repository.getReferenceById(id));
    }
}
