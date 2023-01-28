package com.study.domain.service;

import com.study.domain.dto.AlunosDto;
import com.study.domain.dto.CursosDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CursoService {

    public List<CursosDto> retrieveAll() {
        log.info("Listing cursos");
        return List.of(new CursosDto(1, "Programacao Web II", "API - Rest", "40h"),
                CursosDto.builder().id(2).nome("Programação Orientada a Objetos II").descricao("Java").duracao("50h").build());
    }

    public CursosDto getById(int id) {
        log.info("Getting curso id-{}", id);

        return CursosDto.builder()
                .id(id)
                .nome("Nome do curso")
                .descricao("Descrição do curso")
                .duracao("Duração do curso")
                .build();
    }

    public void save(CursosDto curso) {
        log.info("Saving curso - {}", curso);
    }

    public void update(int id, CursosDto curso) {
        log.info("Updating curso id - {}, data - {}", id, curso);
    }

    public void delete(int id) {
        log.info("Deleting curso id - {}", id);
    }
}
