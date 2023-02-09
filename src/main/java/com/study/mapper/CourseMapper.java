package com.study.mapper;

import com.study.dto.CourseRequestDTO;
import com.study.dto.CourseResponseDTO;
import com.study.model.Course;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CourseMapper {
    public List<CourseResponseDTO> toResponse(List<Course> listOfEntities) {
        if (Objects.isNull(listOfEntities)) return new ArrayList<>();

        return listOfEntities.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public CourseResponseDTO toResponse(Course entity) {
        if (Objects.isNull(entity)) return null;

        return CourseResponseDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .duration(entity.getDuration())
                .build();
    }

    public Course toEntity(CourseRequestDTO request) {
        if (Objects.isNull(request)) return null;

        return Course.builder()
                .name(request.getName())
                .description(request.getDescription())
                .duration(request.getDuration())
                .build();
    }
}