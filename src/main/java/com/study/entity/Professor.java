package com.study.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
@Entity
@Table(name = "PROFESSOR")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "professor_id")
    private int id;

    @Column(name = "professor_nome")
    private String nome;

    @Column(name = "professor_titulo")
    private String titulo;

    @Column(name = "professor_sexo")
    private String sexo;
    @OneToOne (Fetch = FetchType.LAZY, mappedBy = "titular")
    private Disciplina disciplina;
}
