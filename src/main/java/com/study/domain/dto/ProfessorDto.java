package com.study.domain.dto;

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
    private String nome;
    private String genero;

    
    public String toString(){
        return "Id..: " + String.valueOf(this.getId()) + " Nome..:" + this.getNome();
    }

}
