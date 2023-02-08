package com.study.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.study.dto.commons.PessoaDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class AlunoResponse extends PessoaDTO {

    private String matricula;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String tutor;

}