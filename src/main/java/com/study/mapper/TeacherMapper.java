package com.study.mapper;



import com.study.dto.TeacherRequestDTO;
import com.study.dto.TeacherResponseDTO;
import com.study.model.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.stream.*;

@Service
public class TeacherMapper {
    public List<TeacherResponseDTO> toResponse(List<Teacher> listOfEntities) {
        if (Objects.isNull(listOfEntities)) return new ArrayList<>();

        return listOfEntities.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public TeacherResponseDTO toResponse(Teacher entity) {
        if (Objects.isNull(entity)) return null;

        return  TeacherResponseDTO.builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .email(entity.getEmail())
                    .cpf(entity.getCpf())
                    .build();
    }

    public Teacher toEntity(TeacherRequestDTO request) {
         if (Objects.isNull(request)) return null;

         return Teacher.builder()
                 .name(request.getName())
                 .email(request.getEmail())
                 .cpf(request.getCpf())
                 .build();
    }
}