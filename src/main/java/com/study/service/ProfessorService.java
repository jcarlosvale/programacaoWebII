package com.study.service;

import com.study.domain.dto.v1.*;
import lombok.extern.slf4j.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
@Slf4j
public class ProfessorService {

    public List<ProfessorDto> retrieveAll() {
        log.info("Listing professors");
        return List.of(new ProfessorDto(1, "Joao"), ProfessorDto.builder().id(2).name("Maria").build());
    }

    public ProfessorDto getById(int id) {
        log.info("Getting professor id-{}", id);

        return ProfessorDto.builder()
                .id(id)
                .name("Name of professor")
                .build();
    }

    public void save(ProfessorDto professor) {
        log.info("Saving professor - {}", professor);
    }

    public void update(int id, ProfessorDto professor) {
        log.info("Updating professor id - {}, data - {}", id, professor);
    }

    public void delete(int id) {
        log.info("Deleting professor id - {}", id);
    }

}
