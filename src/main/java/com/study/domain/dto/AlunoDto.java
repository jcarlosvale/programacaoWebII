package com.study.domain.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class AlunoDto {
    private int id;
    private String nome;
    private int idade;
    private String matricula;
    private String sexo;

    


    public String toString() { 
            
        return "Id";
    }

}


