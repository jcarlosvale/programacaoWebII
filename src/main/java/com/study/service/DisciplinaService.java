package com.study.service;

import com.study.dto.DisciplinaRequestDto;
import com.study.dto.DisciplinaResponseDto;
import com.study.mapper.DisciplinaMapper;
import com.study.mapper.ProfessorMapper;
import com.study.model.Disciplina;
import com.study.model.Professor;
import com.study.repository.DisciplinaRepository;
import com.study.repository.ProfessorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DisciplinaService {

    private DisciplinaRepository repository;
    private DisciplinaMapper mapper;


    @Autowired
    public DisciplinaService(DisciplinaRepository repository, DisciplinaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<DisciplinaResponseDto> retrieveAll() {
        log.info("Listing professors");
        return mapper.toResponse(repository.findAll());
    }

    public DisciplinaResponseDto getById(int id) {
        log.info("Getting disciplina id-{}", id);
        return mapper.toResponse(repository.findById(id).get());
    }

    public void save(DisciplinaRequestDto disciplina) {
        log.info("Salvando disciplina - {}", disciplina);
        repository.save(mapper.toEntity(disciplina));
    }

    public void update(int id, DisciplinaRequestDto disciplinaRequest) {
        log.info("Alterando disciplina id - {}, data - {}", id, disciplinaRequest);
        Disciplina disciplina = mapper.toEntity(disciplinaRequest);
        disciplina.setId(id);
        repository.save(disciplina);
    }

    public void delete(int id) {
        log.info("Deletenado disciplina id - {}", id);
        repository.deleteById(id);
    }

}
