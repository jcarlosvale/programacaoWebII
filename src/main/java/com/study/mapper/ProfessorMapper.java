package com.study.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.study.dto.ProfessorRequest;
import com.study.dto.ProfessorResponse;
import com.study.entity.ProfessorEntity;

@Service
public class ProfessorMapper {

    public List<ProfessorResponse> toResponse(List<ProfessorEntity> listOfProfessors) {

        if (Objects.isNull(listOfProfessors)) return new ArrayList<>();

        return listOfProfessors.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public ProfessorResponse toResponse(ProfessorEntity entity) {

        if (Objects.isNull(entity)) return null;

        return  ProfessorResponse.builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .email(entity.getEmail())
                    .sexo(entity.getSexo())
                    .cpf(entity.getCpf())
                    .build();
    }

    public ProfessorEntity toEntity(ProfessorRequest request) {
         if (Objects.isNull(request)) {
             return null;
         } else {
             return ProfessorEntity.builder()
                     .name(request.getName())
                     .email(request.getEmail())
                     .cpf(request.getCpf())
                     .sexo(request.getSexo())
                     .build();
         }
    }
}