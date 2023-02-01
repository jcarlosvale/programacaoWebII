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
public class AlunoRequestDTO {

    @NotBlank(message = "O campo não pode estar vazio")
    @Size(min=3, message = "Campo a partir de 3 caracteres")
    private String nome;

    @NotBlank(message = "O campo não pode estar vazio")
    private String matricula;

    @Size(max=1, message = "Digite apenas um caracter")
    private String sexo;
}
