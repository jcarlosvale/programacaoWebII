package com.study.mapper;

import com.study.dto.StudentResponseDTO;
import com.study.dto.TutorResponseDTO;
import com.study.model.*;
import org.springframework.stereotype.*;

import java.time.*;
import java.time.format.*;
import java.util.*;
import java.util.stream.*;

@Service
public class StudentMapper {
    public List<StudentResponseDTO> toResponse(List<Student> listOfEntities) {
        if (Objects.isNull(listOfEntities)) return new ArrayList<>();

        return listOfEntities.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());

    }

    public StudentResponseDTO toResponse(Student entity) {
        Objects.requireNonNull(entity, "entity must not be null");

        var response =
                StudentResponseDTO.builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .build();

        if (Objects.nonNull(entity.getTutor())) {
            response.setTutor(entity.getTutor().getName());
        }

        return response;
    }

    public TutorResponseDTO toResponse(Teacher entity) {
        Objects.requireNonNull(entity, "entity must not be null");

        var formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss");

        return TutorResponseDTO.builder()
                .tutor(entity.getName())
                .updated(formatter.format(LocalDateTime.now()))
                .build();
    }
}
