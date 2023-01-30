package com.study.service;

import com.study.domain.dto.AlunoDto;
import com.study.domain.dto.ProfessorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class ProfessorService {

    private final Map<Integer, ProfessorDto> repository;

    public ProfessorDto save(final ProfessorDto professor) {
            repository.put(professor.getId(), professor);
            return professor;
    }

    public ProfessorDto getById(final int id) {
       return  repository.get(id);
    }

    public List<ProfessorDto> getAll() {
            return new ArrayList<>(repository.values());
    }

    public ProfessorDto update(final int id, final ProfessorDto professor) {
            repository.put(id, professor);
            return professor;
    }

    public void delete(final int id) {
            repository.remove(id);
    }
}
