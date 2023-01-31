package com.study.service;

import com.study.dto.DisciplinaDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DisciplinaService {

    public List<DisciplinaDto> retrieveAll() {
        log.info("Listing professors");
        return List.of(DisciplinaDto.builder().id(1).descricao("JAVA").duracao(6).build(), DisciplinaDto.builder().id(2).descricao("Angular").duracao(6).build());
    }

    public DisciplinaDto getById(int id) {
        log.info("Getting disciplina id-{}", id);

        return DisciplinaDto.builder()
                .id(id)
                .descricao("Nome da disciplina")
                .duracao(6)
                .build();
    }

    public void save(DisciplinaDto disciplina) {
        log.info("Salvando disciplina - {}", disciplina);
    }

    public void update(int id, DisciplinaDto disciplina) {
        log.info("Alterando disciplina id - {}, data - {}", id, disciplina);
    }

    public void delete(int id) {
        log.info("Deletenado disciplina id - {}", id);
    }

}
