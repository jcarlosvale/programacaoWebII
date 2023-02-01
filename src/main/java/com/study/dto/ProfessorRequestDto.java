package com.study.dto;

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
public class ProfessorRequestDto {

    @NotBlank(message = "Name must be not empty or null")
    @Size(min = 2, message = "Minimum name length 2 characters")
    private String name;

    @Email(message = "Email not valid")
    private String email;

    @CPF(message = "CPF not valid")
    private String cpf;

}
