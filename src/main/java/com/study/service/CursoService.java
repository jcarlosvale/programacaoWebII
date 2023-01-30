package com.study.service;

import com.study.domain.dto.AlunoDto;
import com.study.domain.dto.CursoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class CursoService {

    private final Map<Integer, CursoDto> repository;

    public CursoDto save(final CursoDto curso) {
            repository.put(curso.getId(), curso);
            return curso;
    }

    public CursoDto getById(final int id) {
       return  repository.get(id);
    }

    public List<CursoDto> getAll() {
            return new ArrayList<>(repository.values());
    }

    public CursoDto update(final int id, final CursoDto curso) {
            repository.put(id, curso);
            return curso;
    }

    public void delete(final int id) {
            repository.remove(id);
    }
}
