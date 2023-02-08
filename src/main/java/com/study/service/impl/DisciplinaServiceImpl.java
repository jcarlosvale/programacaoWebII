package com.study.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.study.dto.mapper.DisciplinaMapper;
import com.study.dto.request.DisciplinaRequest;
import com.study.dto.response.DisciplinaResponse;
import com.study.entity.Disciplina;
import com.study.repository.DisciplinaRepository;
import com.study.repository.ProfessorRepository;
import com.study.service.DisciplinaService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
    public DisciplinaResponse update(int id, DisciplinaRequest request) {
        Objects.requireNonNull(request, "A requisição não pode ser null");

        Optional<Disciplina> optionalProfessor = repository.findById(id);
        optionalProfessor.orElseThrow(() -> new EntityNotFoundException("Disciplina não encontrado"));

        Disciplina entity = mapper.toEntity(request);
        entity.setId(id);

        repository.save(entity);

        return mapper.toResponse(entity);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

}