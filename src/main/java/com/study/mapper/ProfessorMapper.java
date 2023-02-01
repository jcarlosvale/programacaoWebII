package com.study.mapper;

import com.study.dto.ProfessorRequestDto;
import com.study.dto.ProfessorResponseDto;
import com.study.model.Professor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProfessorMapper {
    public List<ProfessorResponseDto> toResponse(List<Professor> listOfProfessors) {

        if (Objects.isNull(listOfProfessors)) return new ArrayList<>();

        return listOfProfessors.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public ProfessorResponseDto toResponse(Professor entity) {

        if (Objects.isNull(entity)) return null;

        return  ProfessorResponseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .cpf(entity.getCpf())
                .build();
    }

    public Professor toEntity(ProfessorRequestDto request) {
        if (Objects.isNull(request)) {
            return null;
        } else {
            return Professor.builder()
                    .name(request.getName())
                    .email(request.getEmail())
                    .cpf(request.getCpf())
                    .build();
        }
    }
}
