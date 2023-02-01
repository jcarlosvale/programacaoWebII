package com.study.domain.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class Alunos {

    @Positive
    private Long id;
    @NotBlank
    private String nome;
    @NotBlank
    private String matricula;
    @NotBlank
    private String sexo;

}
