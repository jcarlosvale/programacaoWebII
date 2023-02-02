package com.study.domain.service;

import com.study.domain.dto.AlunosRequest;
import com.study.domain.dto.AlunosResponse;
import com.study.domain.mapper.AlunoMapper;
import com.study.domain.model.Alunos;
import com.study.domain.repositories.AlunosRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunosRepository repository;
    private final AlunoMapper mapper;

    public List<AlunosResponse> retrieveAll() {
        log.info("Listing alunos");
        return mapper.toResponse(repository.findAll());
    }

    public AlunosResponse getById(Long id) {
        log.info("Getting aluno id-{}", id);
        var optionalAluno = repository.findById(id);

        if (optionalAluno.isPresent()) {
            return mapper.toResponse(optionalAluno.get());
        }

        return new AlunosResponse();
    }

    public AlunosResponse save(AlunosRequest request) {
        Objects.requireNonNull(request, "request must not be null");

        log.info("Saving aluno - {}", request);

        return mapper.toResponse(repository.save(mapper.toEntity(request)));
    }

    public AlunosResponse update(Long id, AlunosRequest request) {
        Objects.requireNonNull(request, "request must not be null");

        log.info("Updating aluno id - {}, data - {}", id, request);

        var optionalAluno = repository.findById(id);

        optionalAluno.orElseThrow(() -> new EntityNotFoundException("Aluno not found."));

        Alunos entity = mapper.toEntity(request);
        entity.setId(id);

        repository.save(entity);
        return mapper.toResponse(entity);
    }

    public void delete(Long id) {
        log.info("Deleting aluno id - {}", id);
        repository.deleteById(id);
    }
}
