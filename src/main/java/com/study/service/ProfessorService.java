package com.study.service;


import com.study.dto.ProfessorRequestDto;
import com.study.dto.ProfessorResponseDto;
import com.study.mapper.ProfessorMapper;
import com.study.model.Professor;
import com.study.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProfessorService {

    private ProfessorRepository repository;
    private ProfessorMapper mapper;

    @Autowired
    public ProfessorService(ProfessorRepository repository, ProfessorMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<ProfessorResponseDto> retrieveAll() {
        log.info("Listing professors");
        return mapper.toResponse(repository.findAll());
    }

    public ProfessorResponseDto getById(int id) {
        log.info("Getting professor id-{}", id);
        return mapper.toResponse(repository.findById(id).get());
    }

    public void save(ProfessorRequestDto professor) {
        log.info("Saving professor - {}", professor);
        repository.save(mapper.toEntity(professor));
    }

    public void update(int id, ProfessorRequestDto professorRequestDto) {
        log.info("Updating professor id - {}, data - {}", id, professorRequestDto);
        Professor professor = mapper.toEntity(professorRequestDto);
        professor.setId(id);
        repository.save(professor);
    }

    public void delete(int id) {
        log.info("Deleting professor id - {}", id);
        repository.deleteById(id);
    }

}
