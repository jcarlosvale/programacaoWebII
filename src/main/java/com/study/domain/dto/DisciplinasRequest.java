package com.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class DisciplinasRequest {

    @NotBlank(message = "Nome must be not empty or null")
    private String nome;

    @NotBlank(message = "Descrição must be not empty or null")
    private String descricao;

    @NotBlank(message = "Duração must be not empty or null")
    private String duracao;

}
