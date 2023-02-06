package com.study.service.professor.impl;

import com.study.dto.mapper.ProfessorMapper;
import com.study.dto.request.ProfessorRequest;
import com.study.dto.response.ProfessorResponse;
import com.study.entity.Professor;
import com.study.repository.ProfessorRepository;
import com.study.service.professor.ProfessorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProfessorServiceImpl implements ProfessorService {

    private final ProfessorRepository repository;

    private final ProfessorMapper mapper;

    @Override
    public ProfessorResponse createProfessor(ProfessorRequest request) {
        Objects.requireNonNull(request, "A requisição não pode ser null");

        return mapper.toResponse(repository.save(mapper.toEntity(request)));
    }

    @Override
    public ProfessorResponse getById(int id) {
        Optional<Professor> optionalProfessor = repository.findById(id);

        if (optionalProfessor.isPresent()) {
            return mapper.toResponse(optionalProfessor.get());
        }

        return new ProfessorResponse();
    }

    @Override
    public List<ProfessorResponse> getAll() {
        return mapper.toResponse((repository.findAll()));
    }

    @Override
    public ProfessorResponse update(int id, ProfessorRequest request) {
        Objects.requireNonNull(request, "A requisição não pode ser null");

        Optional<Professor> optionalProfessor = repository.findById(id);
        optionalProfessor.orElseThrow(() -> new EntityNotFoundException("Professor não encontrado"));

        Professor entity = mapper.toEntity(request);
        entity.setId(id);

        repository.save(entity);

        return mapper.toResponse(entity);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

}
