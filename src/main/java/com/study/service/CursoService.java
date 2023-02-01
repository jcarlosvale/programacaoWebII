package com.study.service;

import com.study.domain.dto.AlunoRequestDto;
import com.study.domain.dto.AlunoResponseDto;
import com.study.domain.dto.CursoRequestDto;
import com.study.domain.dto.CursoResponseDto;
import com.study.mapper.AlunoMapper;
import com.study.mapper.CursoMapper;
import com.study.model.AlunoModel;
import com.study.model.CursoModel;
import com.study.repository.AlunoRepository;
import com.study.repository.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class CursoService {

    //private final Map<Integer, CursoRequestDto> repository;

    private final CursoRepository repository;
    private final CursoMapper mapper;

    public CursoResponseDto save(final CursoRequestDto curso) {

        return mapper.toResponse(repository.save(mapper.toEntity(curso)));
    }

    public CursoResponseDto getById(final int id) {

        var optionalCursoModel = repository.findById(id);

        if (optionalCursoModel.isPresent()) {
            return mapper.toResponse(optionalCursoModel.get());
        }

        return new CursoResponseDto();


    }

    public List<CursoResponseDto> getAll() {
        return mapper.toResponse(repository.findAll());
    }

    public CursoResponseDto update(final int id, final CursoRequestDto cursoRequest) {
        var optionalCurso = repository.findById(id);

        optionalCurso.orElseThrow(() -> new EntityNotFoundException("Curso not found."));

        CursoModel entity = mapper.toEntity(cursoRequest);
        entity.setId(id);

        repository.save(entity);
        return mapper.toResponse(entity);
    }

    public void delete(final int id) {

        repository.deleteById(id);
    }
}
