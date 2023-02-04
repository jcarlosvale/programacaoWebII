package com.study.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class Disciplina {
    @Positive
    private int id;

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    @Positive
    private int duracao;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "titular")
    private Professor titular;
}
