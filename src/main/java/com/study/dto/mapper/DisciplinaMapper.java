package com.study.dto.mapper;

import com.study.dto.response.DisciplinaResponse;
import com.study.dto.response.TitularResponse;
import com.study.entity.Disciplina;
import com.study.entity.Professor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class DisciplinaMapper {

    public List<DisciplinaResponse> toResponse(List<Disciplina> listOfEntities) {
        if (Objects.isNull(listOfEntities)) return new ArrayList<>();

        return listOfEntities.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public DisciplinaResponse toResponse(Disciplina entity) {
        if (Objects.isNull(entity)) return null;

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss");

        DisciplinaResponse response = DisciplinaResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .cargaHoraria(entity.getCargaHoraria())
                .dateTime(formatter.format(entity.getDateTime()))
                .build();

        if (Objects.nonNull(entity.getTitular())) {
            response.setTitular(entity.getTitular().getName());
        }

        return response;
    }

    public TitularResponse toResponse(Professor entity) {

        if (Objects.isNull(entity)) return null;

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss");

        return TitularResponse.builder()
                .titular(entity.getName())
                .atualizacao(formatter.format(LocalDateTime.now()))
                .build();
    }

}
