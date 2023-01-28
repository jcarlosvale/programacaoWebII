package com.study.domain.dto.v1;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class ProfessorDto {

    private int id;
    private String name;
}