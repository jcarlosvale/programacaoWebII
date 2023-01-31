package com.study.service;


import com.study.dto.v2.*;
import lombok.extern.slf4j.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
@Slf4j
public class ProfessorServiceV2 {

    public List<ProfessorDtoV2> retrieveAll() {
        log.info("Listing professors");
        return List.of(ProfessorDtoV2.builder().id(1).name("Joao").build(), ProfessorDtoV2.builder().id(2).name("Maria").build());
    }

    public ProfessorDtoV2 getById(int id) {
        log.info("Getting professor id-{}", id);

        return ProfessorDtoV2.builder()
                .id(id)
                .name("Name of professor")
                .build();
    }

    public void save(ProfessorDtoV2 professor) {
        log.info("Saving professor - {}", professor);
    }

    public void update(int id, ProfessorDtoV2 professor) {
        log.info("Updating professor id - {}, data - {}", id, professor);
    }

    public void delete(int id) {
        log.info("Deleting professor id - {}", id);
    }

}
