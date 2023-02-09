package com.study.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class StudentResponseDTO {
    private Integer id;

    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String tutor;
}
