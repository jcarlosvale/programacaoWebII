package com.study.mapper;

import com.study.domain.dto.ProfessorRequestDto;
import com.study.domain.dto.ProfessorResponseDto;
import com.study.model.ProfessorModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProfessorMapper {
    public List<ProfessorResponseDto> toResponse(List<ProfessorModel> listOfProfessores) {

        if (Objects.isNull(listOfProfessores)) return new ArrayList<>();

        return listOfProfessores.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public ProfessorResponseDto toResponse(ProfessorModel entity) {

        if (Objects.isNull(entity)) return null;

        return  ProfessorResponseDto.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .titulo(entity.getTitulo())
                .sexo(entity.getSexo())
                .build();
    }

    public ProfessorModel toEntity(ProfessorRequestDto request) {
        if (Objects.isNull(request)) {
            return null;
        } else {
            return ProfessorModel.builder()
                    .nome(request.getNome())
                    .titulo(request.getTitulo())
                    .sexo(request.getSexo())
                    .build();
        }
    }
}
