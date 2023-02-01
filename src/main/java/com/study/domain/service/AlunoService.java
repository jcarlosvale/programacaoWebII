package com.study.domain.service;

import com.study.domain.model.Alunos;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AlunoService {

    public List<Alunos> retrieveAll() {
        log.info("Listing alunos");
        return null;
    }

    public Alunos getById(Long id) {
        log.info("Getting professor id-{}", id);

        return Alunos.builder()
                .id(id)
                .nome("Nome do aluno")
                .matricula("Matricula do aluno")
                .sexo("Sexo do aluno")
                .build();
    }

    public void save(Alunos aluno) {
        log.info("Saving aluno - {}", aluno);
    }

    public void update(Long id, Alunos aluno) {
        log.info("Updating aluno id - {}, data - {}", id, aluno);
    }

    public void delete(Long id) {
        log.info("Deleting aluno id - {}", id);
    }
}
