package com.study.mapper;

import com.study.dto.DisciplinaRequestDto;
import com.study.dto.DisciplinaResponseDto;
import com.study.model.Disciplina;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class DisciplinaMapper {

    public List<DisciplinaResponseDto> toResponse(List<Disciplina> listaDisciplina) {

        if (Objects.isNull(listaDisciplina)) return new ArrayList<>();

        return listaDisciplina.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public DisciplinaResponseDto toResponse(Disciplina entity) {

        if (Objects.isNull(entity)) return null;

        return  DisciplinaResponseDto.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .descricao(entity.getDescricao())
                .duracao(entity.getDuracao())
                .build();
    }

    public Disciplina toEntity(DisciplinaRequestDto request) {
        if (Objects.isNull(request)) {
            return null;
        } else {
            return Disciplina.builder()
                    .nome(request.getNome())
                    .descricao(request.getDescricao())
                    .duracao(request.getDuracao())
                    .build();
        }
    }
}

