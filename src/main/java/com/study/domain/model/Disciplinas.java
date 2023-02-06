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
@Table(name = "DISCIPLINAS")
public class Disciplinas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "disciplina_id")
    private Long id;

    @Column(name = "disciplina_name", nullable = false)
    private String nome;

    private String descricao;

    private String duracao;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "titular")
    private Professores titular;

}
