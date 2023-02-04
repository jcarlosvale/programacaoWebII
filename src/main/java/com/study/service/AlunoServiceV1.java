package com.study.service;


import com.study.dto.v3.*;
import com.study.mapper.*;
import com.study.model.*;
import com.study.repository.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.stereotype.*;

import javax.persistence.*;
import javax.transaction.*;
import javax.validation.*;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class AlunoServiceV1 {

    private final AlunoMapper mapper;
    private final AlunoRepository repository;
    private final ProfessorRepository professorRepository;

    public List<AlunoResponse> retrieveAll() {
        log.info("Listing alunos");
        final var listOfEntities = repository.findAll();
        return  mapper.toResponse(listOfEntities);
    }

    @Transactional
    public AlunoResponse save(@Valid AlunoRequest request) {
        Objects.requireNonNull(request, "request must not be null");

        log.info("Saving aluno - {}", request);

        var entity =
                Aluno.builder()
                        .name(request.getName())
                        .build();

        repository.save(entity);

        return mapper.toResponse(entity);
    }

    public AlunoResponse getById(int id) {
        log.info("Getting aluno id-{}", id);

        var optionalAluno = repository.findById(id);
        var entity = optionalAluno.orElseThrow(() -> new EntityNotFoundException("Aluno not found"));

        return mapper.toResponse(entity);
    }

    @Transactional
    public TutorResponse updateTutor(int idAluno, int idProfessor) {

        log.info("Updating tutor aluno-id: {}, professor-id: {}", idAluno, idProfessor);

        //find entities
        var alunoOptional = repository.findById(idAluno);
        var professorOptional = professorRepository.findById(idProfessor);

        //validate is not empty
        var aluno = alunoOptional.orElseThrow(() -> new EntityNotFoundException("Aluno not found"));
        var professor = professorOptional.orElseThrow(() -> new EntityNotFoundException("Professor not found"));

        //Update
        aluno.setTutor(professor);
        repository.save(aluno);

        return mapper.toResponse(professor);
    }

    public List<AlunoResponse> getTutoradosByProfessorId(int idProfessor) {

        log.info("Getting tutorados by professor-id: {}", idProfessor);

        var professorOptional = professorRepository.findById(idProfessor);
        var professor = professorOptional.orElseThrow(() -> new EntityNotFoundException("Professor not found"));

        List<Aluno> listOfEntities = repository.findAlunosByTutor(professor);

        return mapper.toResponse(listOfEntities);
    }
}
