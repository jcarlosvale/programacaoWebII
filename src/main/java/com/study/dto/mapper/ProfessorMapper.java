package com.study.dto.mapper;

import com.study.dto.request.ProfessorRequest;
import com.study.dto.response.ProfessorResponse;
import com.study.entity.Professor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProfessorMapper {

    public List<ProfessorResponse> toResponse(List<Professor> professres) {
        if (Objects.isNull(professres)) return new ArrayList<>();

        return professres
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public ProfessorResponse toResponse(Professor entity) {
        if (Objects.isNull(entity)) return null;

        ProfessorResponse response = ProfessorResponse.builder().build();;
        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setCpf(entity.getCpf());
        response.setEmail(entity.getEmail());
        response.setTitulo(entity.getTitulo());

        return response;
    }

    public Professor toEntity(ProfessorRequest request) {
        if (Objects.isNull(request)) return null;

        return Professor.builder()
                .name(request.getName())
                .cpf(request.getCpf())
                .email(request.getEmail())
                .titulo(request.getTitulo())
                .build();
    }
}
