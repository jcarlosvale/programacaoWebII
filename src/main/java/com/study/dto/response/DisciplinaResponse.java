package com.study.dto.response;

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
public class DisciplinaResponse {

    @Positive
    private int id;

    @NotBlank
    @Size(min = 10)
    private String name;

    @Size(max = 30)
    private String descricao;

    @Size(max = 4)
    private String cargaHoraria;

}
