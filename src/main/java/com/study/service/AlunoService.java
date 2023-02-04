package com.study.service;

import com.study.dto.AlunoRequestDto;
import com.study.dto.AlunoResponseDto;
import com.study.mapper.AlunoMapper;
import com.study.model.Aluno;
import com.study.model.Disciplina;
import com.study.repository.AlunoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AlunoService {

    private AlunoRepository repository;

    private AlunoMapper mapper;

    @Autowired
    public AlunoService(AlunoRepository repository, AlunoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<AlunoResponseDto> retrieveAll() {
        log.info("Lista Alunos");
        return mapper.toResponse(repository.findAll());
    }

    public AlunoResponseDto getById(int id) {
        log.info("Getting Aluno id-{}", id);

        return mapper.toResponse(repository.findById(id).get());
    }

    public void save(AlunoRequestDto aluno) {

        log.info("Salvando aluno - {}", aluno);
        repository.save(mapper.toEntity(aluno));
    }

    public void update(int id, AlunoRequestDto alunoRequest) {
        log.info("alterando Aluno id - {}, data - {}", id, alunoRequest);
        Aluno aluno = mapper.toEntity(alunoRequest);
        aluno.setId(id);
        repository.save(aluno);
    }

    public void delete(int id) {
        log.info("Deleting Aluno id - {}", id);
        repository.deleteById(id);
    }

}
