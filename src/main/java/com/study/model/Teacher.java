package com.study.model;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TEACHER")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private Integer id;

    @Column(name = "teacher_name", nullable = false)
    private String name;

    private String email;

    private String cpf;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tutor")
    private List<Student> students;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "titular")
    private Subject subject;
}