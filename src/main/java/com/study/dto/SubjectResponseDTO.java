package com.study.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class SubjectResponseDTO {
    private Integer id;
    private String name;
    private String description;
    private List<CourseRequestDTO> courses;
    private String titular;
}
