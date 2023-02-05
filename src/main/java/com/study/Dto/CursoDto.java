package com.study.Dto;

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
public class CursoDto {
    private Integer id;
    private String nome;
    private String descricao;
    private Integer duracao;


    public String toString() {
        
        return "id..........: " + String.valueOf(this.getId()) + 
            "\nNome........: "  + this.getNome() + 
            "\nDescricao...: " + this.getDescricao() + 
            "\nDuração (h).: " + String.valueOf(this.getDuracao());
    }
}
