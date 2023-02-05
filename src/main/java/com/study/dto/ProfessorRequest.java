package com.study.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Getter;
import lombok.Setter;

@Getter //Ver com o professor pq tive que colocar os Getters and Setters na mão
@Setter
public class ProfessorRequest {

    @NotBlank(message = "Name must be not empty or null")
    @Size(min = 2, message = "Minimum name length 2 characters")
    private String name;

    @Email(message = "Email not valid")
    private String email;

    @CPF(message = "CPF not valid")
    private String cpf;
}
