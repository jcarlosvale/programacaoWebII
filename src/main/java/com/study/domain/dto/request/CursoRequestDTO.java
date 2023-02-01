package com.study.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class CursoRequestDTO {

    @NotBlank(message = "O campo não pode estar vazio")
    @Size(min=3, message = "Campo a partir de 3 caracteres")
    private String nome;

    @Size(min=10, message = "Campo a partir de 10 caracteres")
    private String descricao;

    @NotBlank(message = "O campo não pode estar vazio")
    private String cargaHoraria;
}
