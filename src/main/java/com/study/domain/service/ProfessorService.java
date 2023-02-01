package com.study.domain.service;

import com.study.domain.model.Professores;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProfessorService {

    public List<Professores> retrieveAll() {
        log.info("Listing professores");
        return List.of(new Professores(1, "Joao", "Mestre", "M"),
                Professores.builder().id(2).nome("Maria").titulo("Especialista").sexo("F").build());
    }

    public Professores getById(int id) {
        log.info("Getting professor id-{}", id);

        return Professores.builder()
                .id(id)
                .nome("Nome do professor")
                .titulo("Titulo do professor")
                .sexo("Sexo do professor")
                .build();
    }

    public void save(Professores professor) {
        log.info("Saving professor - {}", professor);
    }

    public void update(int id, Professores professor) {
        log.info("Updating professor id - {}, data - {}", id, professor);
    }

    public void delete(int id) {
        log.info("Deleting professor id - {}", id);
    }
}
