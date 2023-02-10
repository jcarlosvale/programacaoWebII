package com.study.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class AlunoRequest {

    @NotBlank(message = "Name must be not empty or null")
    private String nome;

    @Email(message = "Invalid e-mail! ")
    private String email;

    @CPF(message = "Invalid CPF!")
    private String cpf;

    @Size(min = 16, max = 70, message = "Idade must be between 16 and 70 years old!")
    private int idade;

    @NotBlank(message = "Matricula must be not null!")
    private String matricula;

    private String sexo;

    private ProfessorResponse professorResponse;

}