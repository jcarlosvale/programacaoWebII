package com.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
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
public class DisciplinasResponse {

    private Long id;
    private String nome;
    private String descricao;
    private String duracao;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String titular;

}
