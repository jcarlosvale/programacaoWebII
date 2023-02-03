package com.study.domain.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class Curso {
    @Positive
    private int id;

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    @Positive
    private int duracao;
}
