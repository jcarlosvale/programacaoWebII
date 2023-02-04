package com.study.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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
