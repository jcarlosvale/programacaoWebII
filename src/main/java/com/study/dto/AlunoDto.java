package com.study.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class AlunoDto {

    @Positive(message = "Id deve ser ter um valor positivo")
    private int id;

    @NotBlank(message = "Campo não pode ser branco")
    @Size(min = 2, message = "Campo deve ter ao menos 2 caracteres")
    private String nome;

    @NotBlank(message = "Campo não pode ser branco")
    private String matricula;

    @NotBlank(message = "Campo não pode ser branco")
    @Size(max = 1, message = "Campo deve ter ao menos 2 caracteres")
    private String genero;

}
