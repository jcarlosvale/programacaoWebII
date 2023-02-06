package com.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.study.domain.model.Disciplinas;
import com.study.domain.model.Professores;
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
public class ProfessoresRequest {

    @NotBlank(message = "Nome must be not empty or null")
    private String nome;

    @NotBlank(message = "Título must be not empty or null")
    private String titulo;

    @NotBlank(message = "Sexo must be not empty or null")
    private String sexo;

    @NotBlank(message = "Disciplina must be not empty or null")
    private Disciplinas disciplinas;

}
