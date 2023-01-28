package com.study.service;

import com.study.domain.dto.AlunoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class AlunoService {

    private final Map<Integer, AlunoDto> repository;

    public AlunoDto save(final AlunoDto aluno) {
            repository.put(aluno.getId(), aluno);
            return aluno;
    }

    public AlunoDto getById(final int id) {
       return  repository.get(id);
    }

    public List<AlunoDto> getAll() {
            return new ArrayList<>(repository.values());
    }

    public AlunoDto update(final int id, final AlunoDto aluno) {
            repository.put(id, aluno);
            return aluno;
    }

    public void delete(final int id) {
            repository.remove(id);
    }
}
