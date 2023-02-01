package com.study.domain.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class AlunosDto {

    @Positive
    private int id;
    @NotBlank
    private String nome;
    @NotBlank
    private String matricula;
    @NotBlank
    private String sexo;

}
