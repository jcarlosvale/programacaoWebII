package com.study.service;

import com.study.domain.dto.*;
import com.study.mapper.AlunoMapper;
import com.study.mapper.CursoMapper;
import com.study.model.AlunoModel;
import com.study.model.CursoModel;
import com.study.repository.AlunoRepository;
import com.study.repository.CursoRepository;
import com.study.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Slf4j
@Service
public class CursoService {

    //private final Map<Integer, CursoRequestDto> repository;

    private final CursoRepository repository;

    private final ProfessorRepository professorRepository;
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

    @Transactional
    public TitularResponse updateTitular(int idCurso, int idProfessor) {

        log.info("Updating Titular curso-id: {}, professor-id: {}", idCurso, idProfessor);

        //find entities
        var cursoOptional = repository.findById(idCurso);
        var professorOptional = professorRepository.findById(idProfessor);

        //validate is not empty
        var aluno = cursoOptional.orElseThrow(() -> new EntityNotFoundException("Curso not found"));
        var professor = professorOptional.orElseThrow(() -> new EntityNotFoundException("Professor not found"));

        //Update
        aluno.setTitular(professor);
        repository.save(aluno);

        return mapper.toResponse(professor);
    }

    public CursoResponseDto getTitularByProfessorId(int idProfessor) {

        log.info("Getting curso by professor-id: {}", idProfessor);

        var professorOptional = professorRepository.findById(idProfessor);
        var professor = professorOptional.orElseThrow(() -> new EntityNotFoundException("Professor not found"));

        CursoModel entity = repository.findCursoByTitular(professor);

        return mapper.toResponse(entity);
    }
}
