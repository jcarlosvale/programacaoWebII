package com.study.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "DISCIPLINAS")
public class Disciplina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "disciplina_id")
    private int id;

    @Column(name = "disciplina_nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "duracao")
    private int duracao;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "titular")
    private Professor titular;
}
