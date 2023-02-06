package com.study.mapper;


import com.study.dto.v5.*;
import com.study.model.*;
import org.springframework.stereotype.*;

import java.time.*;
import java.time.format.*;
import java.util.*;
import java.util.stream.*;

@Component
public class DisciplinaMapper {

    public List<DisciplinaResponse> toResponse(List<Disciplina> listOfEntities) {

        if (Objects.isNull(listOfEntities)) return new ArrayList<>();

        return listOfEntities.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());

    }

    public DisciplinaResponse toResponse(Disciplina entity) {

        if (Objects.isNull(entity)) return null;

        var formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss");

        var response =
                DisciplinaResponse.builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .dateTime(formatter.format(entity.getDateTime()))
                    .build();

        if (Objects.nonNull(entity.getTitular())) {
            response.setTitular(entity.getTitular().getName());
        }

        return response;
    }

    public TitularResponse toResponse(Professor entity) {

        if (Objects.isNull(entity)) return null;

        var formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss");

        return TitularResponse.builder()
                .titular(entity.getName())
                .atualizacao(formatter.format(LocalDateTime.now()))
                .build();

    }
}
