package com.study.service.aluno.impl;

import com.study.dto.commons.TodoDto;
import com.study.dto.mapper.AlunoMapper;
import com.study.dto.request.AlunoRequest;
import com.study.dto.response.AlunoResponse;
import com.study.dto.response.TutorResponse;
import com.study.entity.Aluno;
import com.study.entity.Professor;
import com.study.repository.AlunoRepository;
import com.study.repository.ProfessorRepository;
import com.study.service.aluno.AlunoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class AlunoServiceImpl implements AlunoService {

    private final AlunoMapper mapper;

    private final AlunoRepository repository ;

    private final RestTemplate restTemplate;

    private final ProfessorRepository professorRepository;


    @Override
    public AlunoResponse createAluno(@Valid  AlunoRequest request) {
        Objects.requireNonNull(request, "O Request não pode ser nulo");

        Aluno aluno = Aluno.builder()
                .name(request.getName())
                .cpf(request.getCpf())
                .email(request.getEmail())
                .build();
        repository.save(aluno);

        return mapper.toResponse(aluno);

    }

    @Override
    public AlunoResponse getById(int id) {

        Optional<Aluno> optionalAluno = repository.findById(id);
        Aluno entity =
                optionalAluno.orElseThrow(() ->
                        new EntityNotFoundException("Aluno não encontrado!")
                );

        return mapper.toResponse(entity);

    }

    @Override
    public List<AlunoResponse> getAll() {

        List<Aluno> listOfEntities = repository.findAll();

        return  mapper.toResponse(listOfEntities);

    }

    @Override
    @Transactional
    public TutorResponse updateTutor(int id, int idProfessor) {

        Optional<Aluno> findAluno = repository.findById(id);
        Optional<Professor> findProfessor = professorRepository.findById(idProfessor);

        Aluno aluno =
                findAluno.orElseThrow(() ->
                        new EntityNotFoundException("Aluno não encontrado!")
                );

        Professor professor =
                findProfessor.orElseThrow(() ->
                        new EntityNotFoundException("Professor não encontrado!")
                );

        aluno.setTutor(professor);

        return mapper.toResponse(professor);

    }

    @Override
    public List<AlunoResponse> getTutoradosByProfessorId(Integer id) {
        Optional<Professor> findProfessor = professorRepository.findById(id);

        Professor professor =
                findProfessor.orElseThrow(() ->
                        new EntityNotFoundException("Professor não encontrado!")
                );

        List<Aluno> alunos = repository.findAlunosByTutor(professor);

        return mapper.toResponse(alunos);
    }

    @Override
    public TodoDto generateRandomTodo() {
        return restTemplate
                .getForEntity("https://www.boredapi.com/api/activity", TodoDto.class)
                .getBody();
    }
}
