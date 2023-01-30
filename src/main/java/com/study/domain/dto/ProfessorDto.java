package com.study.domain.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

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
public class ProfessorDto {
 
    private int id;

    @NotBlank(message = "O Nome precisa ser preenchido!")
    private String nome;

    @CPF(message = "CPF Inválido!")
    private String cpf;

    private String sexo;

    @Email(message = "Email inválido!")
    private String email;
    
    public String toString(){
        return  "Id....: " + String.valueOf(this.getId()) + 
                "\nNome..: " + this.getNome() +
                "\nCPF...: " + this.getCpf() +  
                "\nSexo..: " + this.getSexo() + 
                "\nE-mail: " + this.getEmail();
    }

}
