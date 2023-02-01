package com.study.domain.mapper;

import com.study.domain.dto.CursosRequest;
import com.study.domain.dto.CursosResponse;
import com.study.domain.model.Cursos;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CursoMapper {
    public List<CursosResponse> toResponse(List<Cursos> listOfCursos) {

        if (Objects.isNull(listOfCursos)) return new ArrayList<>();

        return listOfCursos.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public CursosResponse toResponse(Cursos entity) {

        if (Objects.isNull(entity)) return null;

        return CursosResponse.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .descricao(entity.getDescricao())
                .duracao(entity.getDuracao())
                .build();
    }

    public Cursos toEntity(CursosRequest request) {
        if (Objects.isNull(request)) {
            return null;
        } else {
            return Cursos.builder()
                    .nome(request.getNome())
                    .descricao(request.getDescricao())
                    .duracao(request.getDuracao())
                    .build();
        }
    }
}
