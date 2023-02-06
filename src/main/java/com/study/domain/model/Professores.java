package com.study.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CURSOS")
public class Professores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "professor_id")
    private Long id;

    @Column(name = "professor_name", nullable = false)
    private String nome;

    private String titulo;

    private String sexo;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "titular")
    private Disciplinas disciplinas;
}
