package com.study.service;


import com.study.dto.ProfessorRequestDto;
import com.study.dto.ProfessorResponseDto;
import com.study.mapper.ProfessorMapper;
import com.study.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProfessorService {

    private ProfessorRepository repository;
    private ProfessorMapper mapper;

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

    public void update(int id, ProfessorRequestDto professor) {
        log.info("Updating professor id - {}, data - {}", id, professor);
        repository.save(mapper.toEntity(professor));
    }

    public void delete(int id) {
        log.info("Deleting professor id - {}", id);
    }

}
