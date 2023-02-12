package com.study.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.study.dto.DisciplinaResponse;
import com.study.entity.DisciplinaEntity;

@Service
public class DisciplinaMapper {

    public List<DisciplinaResponse> toResponse(List<DisciplinaEntity> listOfEntities) {

        if (Objects.isNull(listOfEntities)) return new ArrayList<>();

        return listOfEntities.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public DisciplinaResponse toResponse(DisciplinaEntity entity) {

        Objects.requireNonNull(entity, "entity must not be null");

        var response =
                DisciplinaResponse.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .descricao(entity.getDescricao())
                .duracao(entity.getDuracao())
                .build();
        if (Objects.nonNull(entity.getTitular())) {
            ((DisciplinaResponse) response).setTitular(entity.getTitular());
        }

        return (DisciplinaResponse) response;
    }


    public DisciplinaEntity toEntity(DisciplinaResponse disciplina) {
        if (Objects.isNull(disciplina)) {
            return null;
        } else {
            var response = DisciplinaEntity.builder()
            .nome(disciplina.getNome())
            .descricao(disciplina.getDescricao())
            .duracao(disciplina.getDuracao())
            .build();
            if (Objects.nonNull(disciplina.getTitular())) {
                response.setTitular(disciplina.getTitular());
            }
            return response;
        }
    }
}