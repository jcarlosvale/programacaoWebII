package com.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.study.domain.model.Disciplinas;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class ProfessoresResponse {

    private Long id;
    private String nome;
    private String titulo;
    private String sexo;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String disciplinas;

}
