package com.study.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class TeacherResponseDTO {
    private Integer id;
    private String name;
    private String email;
    private String cpf;
}