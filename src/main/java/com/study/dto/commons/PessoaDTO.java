package com.study.dto.commons;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class PessoaDTO {

    private Integer id;

    private String name;

    private String email;

    private String cpf;

}