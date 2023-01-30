package com.study.service;

import com.study.dto.v1.*;
import lombok.extern.slf4j.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
@Slf4j
public class ProfessorServiceV1 {

    public List<ProfessorDtoV1> retrieveAll() {
        log.info("Listing professors");
        return List.of(new ProfessorDtoV1(1, "Joao"), ProfessorDtoV1.builder().id(2).name("Maria").build());
    }

    public ProfessorDtoV1 getById(int id) {
        log.info("Getting professor id-{}", id);

        return ProfessorDtoV1.builder()
                .id(id)
                .name("Name of professor")
                .build();
    }

    public void save(ProfessorDtoV1 professor) {
        log.info("Saving professor - {}", professor);
    }

    public void update(int id, ProfessorDtoV1 professor) {
        log.info("Updating professor id - {}, data - {}", id, professor);
    }

    public void delete(int id) {
        log.info("Deleting professor id - {}", id);
    }

}
