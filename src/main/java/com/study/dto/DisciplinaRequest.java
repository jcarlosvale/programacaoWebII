package com.study.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
public class DisciplinaRequest {
@NotBlank(message = "O nome da Disciplina não pode ser nulo ou vazio!")
private String nome;

@Size( min = 10, max = 255, message = "Por favor preencha a descrição com pelo menos 10 caracteres.")
private String descricao;

@Size(min = 8, max = 40, message = "A duração do curso deve ter entre 8h e 40h")
private int duracao;

}
