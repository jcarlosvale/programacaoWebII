package com.study.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class CourseRequestDTO {
    @NotBlank(message = "Name must be not empty or null")
    private String name;

    @NotBlank(message = "Description must be not empty or null")
    private String description;

    @Min(8)
    @Max(40)
    @NotBlank(message = "Duration must be not empty or null")
    private Integer duration;
}
