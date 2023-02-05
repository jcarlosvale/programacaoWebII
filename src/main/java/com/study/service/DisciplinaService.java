package com.study.service;

import com.study.dto.v5.*;
import com.study.mapper.*;
import com.study.model.*;
import com.study.repository.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;

import javax.persistence.*;
import javax.transaction.*;
import javax.validation.*;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class DisciplinaService {

    private final DisciplinaMapper mapper;
    private final DisciplinaRepository repository;
    private final ProfessorRepository professorRepository;

    public List<DisciplinaResponse> retrieveAll() {
        log.info("Listing disciplinas");
        final var listOfEntities = repository.listAll();
        return  mapper.toResponse(listOfEntities);
    }

    @Transactional
    public DisciplinaResponse save(@Valid DisciplinaRequest request) {
        Objects.requireNonNull(request, "request must not be null");

        log.info("Saving disciplina - {}", request);

        var entity =
                Disciplina.builder()
                        .name(request.getName())
                        .build();

        repository.save(entity);

        return mapper.toResponse(entity);
    }

    public DisciplinaResponse getById(int id) {
        log.info("Getting disciplina id-{}", id);

        var entity =
                repository
                        .findByIdNativeQuery(id)
                        .orElseThrow(() -> new EntityNotFoundException("Disciplina not found"));

        return mapper.toResponse(entity);
    }

    public TitularResponse updateTitular(int idDisciplina, int idProfessor) {

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

        return mapper.toResponse(professor);
    }

    public DisciplinaResponse getDisciplinaByProfessorId(int idProfessor) {

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

    public List<DisciplinaResponse> getPage(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("name"));

        Page<Disciplina> pagedResult = repository.findAll(paging);

        if(pagedResult.hasContent()) {
            return mapper.toResponse(pagedResult.getContent());
        } else {
            return List.of();
        }
    }
}
