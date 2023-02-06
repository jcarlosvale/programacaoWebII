package com.study.domain.mapper;

import com.study.domain.dto.ProfessoresRequest;
import com.study.domain.dto.ProfessoresResponse;
import com.study.domain.model.Professores;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProfessorMapper {
    public List<ProfessoresResponse> toResponse(List<Professores> listOfProfessors) {

        if (Objects.isNull(listOfProfessors)) return new ArrayList<>();

        return listOfProfessors.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public ProfessoresResponse toResponse(Professores entity) {

        if (Objects.isNull(entity)) return null;

        return  ProfessoresResponse.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .titulo(entity.getTitulo())
                .sexo(entity.getSexo())
                .disciplinas(entity.getDisciplinas())
                .build();
    }

    public Professores toEntity(ProfessoresRequest request) {
        if (Objects.isNull(request)) {
            return null;
        } else {
            return Professores.builder()
                    .nome(request.getNome())
                    .titulo(request.getTitulo())
                    .sexo(request.getSexo())
                    .disciplinas(request.getDisciplinas())
                    .build();
        }
    }
}
