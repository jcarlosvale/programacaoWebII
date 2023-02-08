package com.study.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class ProfessorResponse extends PessoaDTO {

    private String titulo;

}