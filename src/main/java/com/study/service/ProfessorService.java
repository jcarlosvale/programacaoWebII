package com.study.service;

import com.study.domain.dto.AlunoResponseDto;
import com.study.domain.dto.ProfessorRequestDto;
import com.study.domain.dto.ProfessorResponseDto;
import com.study.mapper.ProfessorMapper;
import com.study.model.AlunoModel;
import com.study.model.ProfessorModel;
import com.study.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class ProfessorService {

    //private final Map<Integer, ProfessorRequestDto> repository;
    private final ProfessorRepository repository;
    private final ProfessorMapper mapper;

    public ProfessorResponseDto save(final ProfessorRequestDto professor) {
        return mapper.toResponse(repository.save(mapper.toEntity(professor)));
    }

    public ProfessorResponseDto getById(final int id) {

        var optionalProfessorModel = repository.findById(id);

        if (optionalProfessorModel.isPresent()) {
            return mapper.toResponse(optionalProfessorModel.get());
        }

        return new ProfessorResponseDto();

    }

    public List<ProfessorResponseDto> getAll() {

        return mapper.toResponse(repository.findAll());
    }

    public ProfessorResponseDto update(final int id, final ProfessorRequestDto professorRequest) {
        var optionalProfessor = repository.findById(id);

        optionalProfessor.orElseThrow(() -> new EntityNotFoundException("Professor not found."));

        ProfessorModel entity = mapper.toEntity(professorRequest);
        entity.setId(id);

        repository.save(entity);
        return mapper.toResponse(entity);
    }

    public void delete(final int id) {
        repository.deleteById(id);
    }
}
