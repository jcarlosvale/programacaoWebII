package com.study.mapper;

import com.study.domain.dto.AlunoRequestDto;
import com.study.domain.dto.AlunoResponseDto;
import com.study.model.AlunoModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AlunoMapper {
    public List<AlunoResponseDto> toResponse(List<AlunoModel> listOfAlunos) {

        if (Objects.isNull(listOfAlunos)) return new ArrayList<>();

        return listOfAlunos.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public AlunoResponseDto toResponse(AlunoModel entity) {

        if (Objects.isNull(entity)) return null;

        return  AlunoResponseDto.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .matricula(entity.getMatricula())
                .sexo(entity.getSexo())
                .build();
    }

    public AlunoModel toEntity(AlunoRequestDto request) {
        if (Objects.isNull(request)) {
            return null;
        } else {
            return AlunoModel.builder()
                    .nome(request.getNome())
                    .matricula(request.getMatricula())
                    .sexo(request.getSexo())
                    .build();
        }
    }
}
