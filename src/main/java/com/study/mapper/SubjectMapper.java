package com.study.mapper;

import com.study.dto.SubjectRequestDTO;
import com.study.dto.SubjectResponseDTO;
import com.study.model.Subject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SubjectMapper {
    public List<SubjectResponseDTO> toResponse(List<Subject> listOfEntities) {
        if (Objects.isNull(listOfEntities)) return new ArrayList<>();

        return listOfEntities.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());

    }

    public SubjectResponseDTO toResponse(Subject entity) {
        Objects.requireNonNull(entity, "entity must not be null");

        var response =
                SubjectResponseDTO.builder()
                        .id(entity.getId())
                        .name(entity.getName())
                        .description(entity.getDescription())
                        .build();

        if (Objects.nonNull(entity.getTitular())) {
            response.setTitular(entity.getTitular().getName());
        }

        return response;
    }

    public Subject toEntity(SubjectRequestDTO request) {
        if (Objects.isNull(request)) return null;

        return Subject.builder()
                .name(request.getName())
                .description(request.getDescription())
                .titular(request.getTitular())
                .build();
    }
}
