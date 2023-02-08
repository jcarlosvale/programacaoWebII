package com.study.dto.mapper;

import com.study.dto.request.DisciplinaRequest;
import com.study.dto.response.DisciplinaResponse;
import com.study.entity.Disciplina;
import org.springframework.stereotype.Service;

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

        DisciplinaResponse response = DisciplinaResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .cargaHoraria(entity.getCargaHoraria())
                .descricao(entity.getDescricao())
                .build();

        if (Objects.nonNull(entity.getTitular())) {
            response.setTitular(entity.getTitular().getName());
        }

        return response;
    }

    public Disciplina toEntity(DisciplinaRequest request) {
        if (Objects.isNull(request)) return null;

        return Disciplina.builder()
                .name(request.getName())
                .cargaHoraria(request.getCargaHoraria())
                .descricao(request.getDescricao())
                .build();
    }


}