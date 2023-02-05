package com.study.model;

import lombok.*;

import javax.persistence.*;
import java.time.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "DISCIPLINAS")
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "disciplina_id")
    private Integer id;

    @Column(name = "disciplina_name", nullable = false)
    private String name;

    @Column(name="data_atualizacao", nullable = false)
    private LocalDateTime dateTime;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "titular", unique = true)
    private Professor titular;

    @PrePersist
    public void prePersist(){
        setDateTime(LocalDateTime.now());
    }

}
