package com.study.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class AlunoRequestDto {

    @Positive
    private int id;

    @NotBlank
    private String nome;

    @Positive
    private int matricula;

    @NotBlank
    private String genero;
}
