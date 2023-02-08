package com.study.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PROFESSORES")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "professor_id")
    private Integer id;

    @Column(name = "professor_name", nullable = false)
    private String name;

    private String email;

    private String cpf;

    @Column(nullable = false)
    private String titulo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tutor")
    private List<Aluno> alunos;


}