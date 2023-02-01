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
        return List.of(new Alunos(1, "Joao", "123", "M"),
                Alunos.builder().id(2).nome("Maria").matricula("456").sexo("F").build());
    }

    public Alunos getById(int id) {
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

    public void update(int id, Alunos aluno) {
        log.info("Updating aluno id - {}, data - {}", id, aluno);
    }

    public void delete(int id) {
        log.info("Deleting aluno id - {}", id);
    }
}
