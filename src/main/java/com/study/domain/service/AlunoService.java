package com.study.domain.service;

import com.study.domain.dto.AlunosDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AlunoService {

    public List<AlunosDto> retrieveAll() {
        log.info("Listing alunos");
        return List.of(new AlunosDto(1, "Joao", "123", "M"),
                AlunosDto.builder().id(2).nome("Maria").matricula("456").sexo("F").build());
    }

    public AlunosDto getById(int id) {
        log.info("Getting professor id-{}", id);

        return AlunosDto.builder()
                .id(id)
                .nome("Nome do aluno")
                .matricula("Matricula do aluno")
                .sexo("Sexo do aluno")
                .build();
    }

    public void save(AlunosDto aluno) {
        log.info("Saving aluno - {}", aluno);
    }

    public void update(int id, AlunosDto aluno) {
        log.info("Updating aluno id - {}, data - {}", id, aluno);
    }

    public void delete(int id) {
        log.info("Deleting aluno id - {}", id);
    }
}
