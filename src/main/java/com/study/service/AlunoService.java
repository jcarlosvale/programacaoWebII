package com.study.service;

import com.study.dto.AlunoRequest;
import com.study.dto.AlunoResponse;
import com.study.dto.TodoDto;
import com.study.dto.TutorResponse;
import com.study.mapper.*;
import com.study.model.*;
import com.study.repository.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.stereotype.*;
import org.springframework.web.client.*;

import javax.persistence.*;
import javax.transaction.*;
import javax.validation.*;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoMapper mapper;
    private final AlunoRepository repository;
    private final ProfessorRepository professorRepository;
    private final RestTemplate restTemplate;


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

    public TodoDto generateRandomTodo() {
        return restTemplate
                .getForEntity("https://www.boredapi.com/api/activity", TodoDto.class)
                .getBody();
    }

    public AlunoResponse update(int id, AlunoRequest request) {
        Optional<Aluno> alunoOptional = repository.findById(id);
        alunoOptional.orElseThrow(() -> new EntityNotFoundException("Aluno not found."));

        Aluno aluno = mapper.toEntity(request);
        aluno.setId(id);

        repository.save(aluno);
        return mapper.toResponse(aluno);
    }

    public void delete(int id) {
        log.info("Deleting professor id - {}", id);
        repository.deleteById(id);
    }
}
