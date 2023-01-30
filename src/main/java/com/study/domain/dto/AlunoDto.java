package com.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class AlunoDto {

    @Positive
    private int id;

    @NotBlank
    @Size(min = 2)
    private String nome;

    @NotBlank
    @Size(min = 2)
    private String matricula;

    @NotBlank
    private String sexo;
}
