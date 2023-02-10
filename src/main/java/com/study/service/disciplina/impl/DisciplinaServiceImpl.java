package com.study.service.disciplina.impl;

import com.study.dto.mapper.DisciplinaMapper;
import com.study.dto.request.DisciplinaRequest;
import com.study.dto.response.DisciplinaResponse;
import com.study.dto.response.TitularResponse;
import com.study.entity.Disciplina;
import com.study.entity.Professor;
import com.study.repository.DisciplinaRepository;
import com.study.repository.ProfessorRepository;
import com.study.service.disciplina.DisciplinaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class DisciplinaServiceImpl implements DisciplinaService {

    private final DisciplinaRepository repository;

    private final DisciplinaMapper mapper;
    private final ProfessorRepository professorRepository;

    @Override
    public DisciplinaResponse createDisciplina(DisciplinaRequest request) {
        Objects.requireNonNull(request, "O request não pode ser nulo");

        Disciplina entity = Disciplina.builder()
                .name(request.getName())
                .build();
        repository.save(entity);

        return mapper.toResponse(entity);
    }

    @Override
    public DisciplinaResponse getById(int id) {
        Optional<Disciplina> optionalDisciplina = repository.findById(id);

        if (optionalDisciplina.isPresent()) {
            return mapper.toResponse(optionalDisciplina.get());
        }

        return new DisciplinaResponse();
    }

    @Override
    public List<DisciplinaResponse> getAll() {
      return mapper.toResponse(repository.findAll());
    }

    @Override
    public TitularResponse update(int id, int idProfessor) {
        Disciplina disciplina =
                repository
                        .findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Disciplina não encontrada!"));

        Professor professor =
                professorRepository
                        .findById(idProfessor)
                        .orElseThrow(() -> new EntityNotFoundException("Professor não encontrado"));

        Optional<Disciplina> query =
                repository.findByTitular(professor);

        query.ifPresent(d -> {
            throw new IllegalStateException("Professor must have at most one Disciplina as titular");
        });

        disciplina.setTitular(professor);
        repository.save(disciplina);

        return mapper.toResponse(professor);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public DisciplinaResponse getDisciplinaByProfessorId(int id) {
        Professor professor =
                professorRepository.findById(id)
                        .orElseThrow(() ->
                                new EntityNotFoundException("Professor não encontrado")
                        );

        Disciplina disciplina
                = repository.findByTitular(professor)
                .orElseThrow(() -> new EntityNotFoundException("Disciplina não encontrada")
                );

        return mapper.toResponse(disciplina);
    }

}
