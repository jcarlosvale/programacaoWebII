package com.study.dto;

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
public class ProfessorResponse {

    private int id;
    private String name;
    private String email;
    private String cpf;
    private String sexo;

/*     public String toString(){
        return "ID - " + id + " Nome - " + name + " CPF - " + cpf + " Email - " + email;
    }
 */
}