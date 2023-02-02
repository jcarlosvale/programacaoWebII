package com.study.model;

import lombok.*;

import javax.persistence.*;

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

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "parceria")
    private Aluno alunoParceiro;



    /*
    @OneToMany
    private Set<Aluno> tutorados;
    */
}