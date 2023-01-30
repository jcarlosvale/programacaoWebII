package com.study.service;

import com.study.dto.AlunoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AlunoService {

    public List<AlunoDto> retrieveAll() {
        log.info("Lista Alunos");
        return java.util.List.of(AlunoDto.builder().id(1).nome("Rogerio").genero("Masculino").matricula("123456").build(),
                AlunoDto.builder().id(2).nome("Bruno").genero("Masculino").matricula("654321").build());
    }

    public AlunoDto getById(int id) {
        log.info("Getting Aluno id-{}", id);

        return AlunoDto.builder()
                .id(2)
                .nome("Nome aluno")
                .genero("Genero Aluno")
                .matricula("Matricula Aluno")
                .build();
    }

    public void save(AlunoDto aluno) {
        log.info("Salvando aluno - {}", aluno);
    }

    public void update(int id, AlunoDto aluno) {
        log.info("alterando Aluno id - {}, data - {}", id, aluno);
    }

    public void delete(int id) {
        log.info("Deleting Aluno id - {}", id);
    }

}
