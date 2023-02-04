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
@Table(name = "CURSO")
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "curso_id")
    private int id;

    @Column(name = "curso_nome", nullable = false)
    private String nome;

    @Column(name = "curso_descricao", nullable = false)
    private String descricao;

    @Column(name = "curso_carga_horaria", nullable = false)
    private String cargaHoraria;
    @OneToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "titular")
    private Professor titular;
  
}
