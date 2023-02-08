package com.study.service;

import com.study.dto.DisciplinaResponse;
import com.study.mapper.DisciplinaMapper;
import com.study.model.Disciplina;
import com.study.model.Professor;
import com.study.repository.DisciplinaRepository;
import com.study.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DisciplinaService {

    private final DisciplinaRepository repository;
    private final ProfessorRepository professorRepository;
    private final DisciplinaMapper mapper;

    public DisciplinaResponse getById(int id) {
        Optional<Disciplina> optionalDisciplina = repository.findById(id);
        optionalDisciplina.orElseThrow(() -> new EntityNotFoundException("Not found disciplina " + id));
        return mapper.toResponse(optionalDisciplina.get());
    }

    public DisciplinaResponse updateTitular(int disciplinaId, int professorId) {
        Optional<Professor> optionalProfessor = professorRepository.findById(professorId);
        optionalProfessor.orElseThrow(() -> new EntityNotFoundException("Not found Professor " + professorId));
        Professor professor = optionalProfessor.get();
        Optional<Disciplina> optionalDisciplina = repository.findById(disciplinaId);
        optionalDisciplina.orElseThrow(() -> new EntityNotFoundException("Not found disciplina " + disciplinaId));
        Disciplina disciplina = optionalDisciplina.get();
        Professor oldTitular = disciplina.getTitular();
        disciplina.setTitular(professor);
        professor.setDisciplina(disciplina);
        repository.save(disciplina);
        professorRepository.save(professor);
        if(oldTitular != null) {
            oldTitular.setDisciplina(null);
            professorRepository.save(oldTitular);
        }
        return mapper.toResponse(disciplina);
    }
}
