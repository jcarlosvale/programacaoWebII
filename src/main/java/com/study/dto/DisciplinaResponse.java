package com.study.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class DisciplinaResponse {
    
    private int id;
    private String nome;
    private String descricao;
    private int duracao;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String titular;

}
