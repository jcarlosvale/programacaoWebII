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
public class ProfessorRequest {

    @NotBlank(message = "O nome não pode ser vazio nem nulo")
    @Size(min = 2, message = "Deve inserir no mínimo 2 caracteres")
    private String name;

    @Email(message = "Email inválido")
    private String email;

    @CPF(message = "CPF invalido")
    private String cpf;

    @Size(min = 5, message = "Inisita no mínimo 5 caracteres para o titulo")
    private String titulo;

}