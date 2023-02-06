package com.study.domain.service;

import com.study.domain.dto.DisciplinasRequest;
import com.study.domain.dto.DisciplinasResponse;
import com.study.domain.dto.ProfessoresResponse;
import com.study.domain.mapper.DisciplinaMapper;
import com.study.domain.mapper.ProfessorMapper;
import com.study.domain.model.Disciplinas;
import com.study.domain.repositories.DisciplinaRepository;
import com.study.domain.repositories.ProfessoresRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class DisciplinaService {

    private final DisciplinaRepository repository;
    private final DisciplinaMapper mapper;
    private final ProfessoresRepository professorRepository;

    private final ProfessorMapper professorMapper;

    public List<DisciplinasResponse> retrieveAll() {
        log.info("Listing cursos");
        return mapper.toResponse(repository.findAll());
    }

    public DisciplinasResponse getById(Long id) {
        log.info("Getting curso id-{}", id);
        var optionalCurso = repository.findById(id);

        if (optionalCurso.isPresent()) {
            return mapper.toResponse(optionalCurso.get());
        }

        return new DisciplinasResponse();
    }

    @Transactional
    public DisciplinasResponse save(DisciplinasRequest request) {
        Objects.requireNonNull(request, "request must not be null");

        log.info("Saving curso - {}", request);

        return mapper.toResponse(repository.save(mapper.toEntity(request)));
    }

    public DisciplinasResponse update(Long id, DisciplinasRequest request) {
        Objects.requireNonNull(request, "request must not be null");

        log.info("Updating curso id - {}, data - {}", id, request);

        var optionalCurso = repository.findById(id);

        optionalCurso.orElseThrow(() -> new EntityNotFoundException("Curso not found."));

        Disciplinas entity = mapper.toEntity(request);
        entity.setId(id);

        repository.save(entity);
        return mapper.toResponse(entity);
    }

    public void delete(Long id) {
        log.info("Deleting curso id - {}", id);
        repository.deleteById(id);
    }

    public ProfessoresResponse updateTitular(Long idDisciplina, Long idProfessor) { //Patch

        log.info("Updating titular disciplina-id: {}, professor-id: {}", idDisciplina, idProfessor);

        //find entities
        var disciplina =
                repository
                        .findById(idDisciplina)
                        .orElseThrow(() -> new EntityNotFoundException("Disciplina not found"));

        var professor =
                professorRepository
                        .findById(idProfessor)
                        .orElseThrow(() -> new EntityNotFoundException("Professor not found"));

        //verify if Professor has no Disciplina
        var query = repository.findByTitular(professor);

        query.ifPresent(d -> {
            throw new IllegalStateException("Professor must have at most one Disciplina as titular");
        });

        //Update
        disciplina.setTitular(professor);
        repository.save(disciplina);

        return professorMapper.toResponse(professor);
    }

    public DisciplinasResponse getDisciplinaByProfessorId(Long idProfessor) { // Get Disciplina por Professor

        log.info("Getting disciplina by professor-id: {}", idProfessor);

        var professor =
                professorRepository
                        .findById(idProfessor)
                        .orElseThrow(() -> new EntityNotFoundException("Professor not found"));

        var disciplina =
                repository
                        .findByTitular(professor)
                        .orElseThrow(() -> new EntityNotFoundException("Disciplina not found"));


        return mapper.toResponse(disciplina);

    }

    public List<DisciplinasResponse> getPage(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("name"));

        Page<Disciplinas> pagedResult = repository.findAll(paging);

        if(pagedResult.hasContent()) {
            return mapper.toResponse(pagedResult.getContent());
        } else {
            return List.of();
        }
    }
}
