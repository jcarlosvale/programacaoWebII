package com.study.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CPF;

import io.micrometer.core.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="ALUNOS")


public class AlunoEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private int id;

    @NotBlank(message = "Name must be not empty or null")
    @Column(name="NOME")
    private String nome;


    @Email(message = "Invalid e-mail!")
    @Column(name="EMAIL")
    private String email;

    
    @CPF(message = "Invalid CPF!")
    @Column(name="CPF")
    private String cpf;

    @Column(name="IDADE")
    private int idade;

    @Column(name="MATRICULA")
    private String matricula;

    @Column(name="SEXO")
    private String sexo;    

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tutor")
    @Nullable
    private ProfessorEntity tutor;

        
    /*
    @Modifying
    @Query("update ALUNOS   u set u.NOME = ?1, u.EMAIL = ?2, u.CPF = ?3, u.IDADE = ?4, u.MATRICULA = ?5, u.SEXO = ?6 where u.ID = ?7")
    public void alunoUpdate(String nome, String email, String cpf, Integer idade, String matricula, String sexo, Integer id){};
    */
}
