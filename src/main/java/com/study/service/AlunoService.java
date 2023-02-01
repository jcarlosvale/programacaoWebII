package com.study.service;

import com.study.domain.dto.AlunoRequestDto;
import com.study.domain.dto.AlunoResponseDto;
import com.study.mapper.AlunoMapper;
import com.study.model.AlunoModel;
import com.study.repository.AlunoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class AlunoService {

    //private final Map<Integer, AlunoRequestDto> repository;
    private final AlunoRepository repository;
    private final AlunoMapper mapper;

    public AlunoResponseDto save(final AlunoRequestDto aluno) {

            return mapper.toResponse(repository.save(mapper.toEntity(aluno)));
    }

    public AlunoResponseDto getById(final int id) {

        var optionalAlunoModel = repository.findById(id);
/*
        return optionalAlunoModel.ifPresentOrElse(
                alunoModel -> mapper.toResponse(alunoModel),
                () -> new AlunoResponseDto());
 */

        if (optionalAlunoModel.isPresent()) {
            return mapper.toResponse(optionalAlunoModel.get());
        }

        return new AlunoResponseDto();


    }

    public List<AlunoResponseDto> getAll() {
        return mapper.toResponse(repository.findAll());
    }

    public AlunoResponseDto update(final int id, final AlunoRequestDto alunoRequest) {
        var optionalAluno = repository.findById(id);

        optionalAluno.orElseThrow(() -> new EntityNotFoundException("Aluno not found."));

        AlunoModel entity = mapper.toEntity(alunoRequest);
        entity.setId(id);

        repository.save(entity);
        return mapper.toResponse(entity);
    }

    public void delete(final int id) {

        repository.deleteById(id);
    }
}
