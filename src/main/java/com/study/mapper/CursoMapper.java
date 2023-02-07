package com.study.mapper;

import com.study.domain.dto.*;
import com.study.model.AlunoModel;
import com.study.model.CursoModel;
import com.study.model.ProfessorModel;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CursoMapper {
    public List<CursoResponseDto> toResponse(List<CursoModel> listOfCursos) {

        if (Objects.isNull(listOfCursos)) return new ArrayList<>();

        return listOfCursos.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public CursoResponseDto toResponse(CursoModel entity) {

        if (Objects.isNull(entity)) return null;

        var response =  CursoResponseDto.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .descricao(entity.getDescricao())
                .horas(entity.getHoras())
                .build();

        if (Objects.nonNull(entity.getTitular())) {
            response.setTitular(entity.getTitular().getNome());
        }

        return response;
    }

    public CursoModel toEntity(CursoRequestDto request) {
        if (Objects.isNull(request)) {
            return null;
        } else {
            return CursoModel.builder()
                    .nome(request.getNome())
                    .descricao(request.getDescricao())
                    .horas(request.getHoras())
                    .build();
        }
    }

    public TitularResponse toResponse(ProfessorModel entity) {

        Objects.requireNonNull(entity, "entity must not be null");

        var formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss");

        return TitularResponse.builder()
                .titular(entity.getNome())
                .atualizacao(formatter.format(LocalDateTime.now()))
                .build();

    }
}
