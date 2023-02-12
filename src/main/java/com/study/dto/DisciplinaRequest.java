package com.study.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class DisciplinaRequest {

    @NotBlank(message = "O nome da Disciplina não pode ser nulo ou vazio!")
    private String nome;

    @NotBlank(message = "Por favor preencha a descrição com pelo menos 10 caracteres.")
    private String descricao;

    @Min(10)
    @Max(255)
    private int duracao;

    private ProfessorResponse titularProfResponse;

}
