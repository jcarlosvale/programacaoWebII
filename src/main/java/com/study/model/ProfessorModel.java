package com.study.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PROFESSORES")
public class ProfessorModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "professor_id")
    private int id;

    @Column(nullable = false)
    private String nome;

    private String titulo;

   private String sexo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tutor")
    private List<AlunoModel> alunos;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "titular")
    private CursoModel curso;
}
