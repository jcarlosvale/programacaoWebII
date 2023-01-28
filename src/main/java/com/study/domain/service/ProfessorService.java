package com.study.domain.service;

import com.study.domain.dto.ProfessoresDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProfessorService {

    public List<ProfessoresDto> retrieveAll() {
        log.info("Listing professores");
        return List.of(new ProfessoresDto(1, "Joao", "Mestre", "M"),
                ProfessoresDto.builder().id(2).nome("Maria").titulo("Especialista").sexo("F").build());
    }

    public ProfessoresDto getById(int id) {
        log.info("Getting professor id-{}", id);

        return ProfessoresDto.builder()
                .id(id)
                .nome("Nome do professor")
                .titulo("Titulo do professor")
                .sexo("Sexo do professor")
                .build();
    }

    public void save(ProfessoresDto professor) {
        log.info("Saving professor - {}", professor);
    }

    public void update(int id, ProfessoresDto professor) {
        log.info("Updating professor id - {}, data - {}", id, professor);
    }

    public void delete(int id) {
        log.info("Deleting professor id - {}", id);
    }
}
