package com.study.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.study.model.Teacher;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class SubjectRequestDTO {
    @Size(min = 2, message = "Minimum name length 2 characters")
    private String name;

    private String description;

    private Teacher titular;
}
