package com.study.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
@Entity
@Table(name = "ALUNO")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aluno_id")
    private int id;

    @Column(name = "aluno_nome", nullable = false)
    private String nome;

    @Column(name = "aluno_matricula", nullable = false)
    private String matricula;

    @Column(name = "aluno_sexo", nullable = false)
    private String sexo;
}
