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
public class DisciplinaDto {

    @Positive(message = "Id deve ser ter um valor positivo")
    private int id;

    @NotBlank(message = "Campo não pode ser branco")
    @Size(min = 2, message = "Campo deve ter ao menos 2 caracteres")
    private String nome;

    @NotBlank(message = "Campo não pode ser branco")
    @Size(min = 10, message = "Campo deve ter ao menos 10 caracteres")
    private String descricao;

    @Positive(message = "Campo não pode ser branco")
    private int duracao;
}
