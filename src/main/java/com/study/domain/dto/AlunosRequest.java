package com.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class AlunosRequest {

    @NotBlank(message = "Nome must be not empty or null")
    private String nome;

    @NotBlank(message = "Matricula must be not empty or null")
    private String matricula;

    @NotBlank(message = "Sexo must be not empty or null")
    private String sexo;

}
