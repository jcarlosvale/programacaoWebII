package com.study.service;

import com.study.dto.AlunoResponse;
import com.study.dto.DisciplinaResponse;
import com.study.dto.ProfessorRequest;
import com.study.dto.ProfessorResponse;
import com.study.mapper.*;
import com.study.model.*;
import com.study.repository.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.stereotype.*;

import javax.persistence.*;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProfessorService {

    private final ProfessorRepository repository;
    private final ProfessorMapper mapper;
    private final DisciplinaMapper disciplinaMapper;

    private final AlunoMapper alunoMapper;


    public List<ProfessorResponse> retrieveAll() {
        log.info("Listing professors");
        return mapper.toResponse(repository.findAll());
    }

    public ProfessorResponse getById(int id) {
        log.info("Getting professor id-{}", id);

        var optionalProfessor = repository.findById(id);

        if (optionalProfessor.isPresent()) {
            return mapper.toResponse(optionalProfessor.get());
        }

        return new ProfessorResponse();
    }

    public ProfessorResponse save(ProfessorRequest request) {

        Objects.requireNonNull(request, "request must not be null");

        log.info("Saving professor - {}", request);

        return mapper.toResponse(repository.save(mapper.toEntity(request)));
    }

    public ProfessorResponse update(int id, ProfessorRequest request) {
        Objects.requireNonNull(request, "request must not be null");
        log.info("Updating professor id - {}, data - {}", id, request);
        var optionalProfessor = repository.findById(id);
        optionalProfessor.orElseThrow(() -> new EntityNotFoundException("Professor not found."));
        Professor entity = mapper.toEntity(request);
        entity.setId(id);
        repository.save(entity);
        return mapper.toResponse(entity);
    }

    public void delete(int id) {
        log.info("Deleting professor id - {}", id);
        repository.deleteById(id);
    }

    public DisciplinaResponse getProfessorDisciplina(int professorId) {
        Optional<Professor> optionalProfessor = repository.findById(professorId);
        optionalProfessor.orElseThrow(() -> new EntityNotFoundException("Not found professor " + professorId));
        Disciplina disciplina = optionalProfessor.get().getDisciplina();
        return disciplinaMapper.toResponse(disciplina);
    }

    public List<AlunoResponse> getProfessorTutorados(int professorId) {
        Optional<Professor> optionalProfessor = repository.findById(professorId);
        optionalProfessor.orElseThrow(()-> new EntityNotFoundException("Not found professor ID " + professorId ));

        Professor professor = optionalProfessor.get();
        return alunoMapper.toResponse(professor.getAlunos());
    }
}
