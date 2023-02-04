package com.study.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class AlunoRequest {

    @Email(message = "Email inválido")
    private String email;

    @CPF(message = "CPF invalido")
    private String cpf;

    @NotBlank(message = "O nome não pode ser vazio ou nulo")
    private String name;

    @Size(min = 5, max = 15)
    private String matricula;

}
