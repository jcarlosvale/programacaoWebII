package com.study.model;

import lombok.*;
import org.hibernate.validator.constraints.br.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.*;

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

    @Email
    private String email;

    @CPF
    private String cpf;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tutor")
    private List<Aluno> alunos;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "titular")
    private Disciplina disciplina;
}