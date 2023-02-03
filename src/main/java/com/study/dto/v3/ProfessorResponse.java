package com.study.dto.v3;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class ProfessorResponse {

    private int id;
    private String name;
    private String email;
    private String cpf;
}