package com.study.domain.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.validation.constraints.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class AlunoDto {
    @Positive
    private int id;

    @NotBlank
    private String nome;

    @Positive
    private int matricula;

    @NotBlank
    private String genero;
}