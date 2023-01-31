package com.study.commons;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class Pessoa {

    @Positive
    private Integer id;

    @NotBlank
    @Size(min = 4)
    private String name;

    @NotBlank
    @Size(min = 1)
    private String sexo;

    @Email(message = "O e-mail inserido está inválido")
    private String email;

    @CPF(message = "CPF invalido, adicione um CPF que seja válido")
    private String cpf;

}
