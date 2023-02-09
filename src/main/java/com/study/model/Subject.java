package com.study.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SUBJECT")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id")
    private Integer id;

    @NotBlank(message = "Name must be not empty or null")
    @Column(name = "subject_name", nullable = false)
    private String name;

    private String description;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "titular", unique = true)
    private Teacher titular;

}
