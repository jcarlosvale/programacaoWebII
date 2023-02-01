package com.study.mapper;

import com.study.domain.dto.AlunoRequestDto;
import com.study.domain.dto.AlunoResponseDto;
import com.study.domain.dto.CursoRequestDto;
import com.study.domain.dto.CursoResponseDto;
import com.study.model.AlunoModel;
import com.study.model.CursoModel;
import org.springframework.stereotype.Service;

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

        return  CursoResponseDto.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .descricao(entity.getDescricao())
                .horas(entity.getHoras())
                .build();
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
}
