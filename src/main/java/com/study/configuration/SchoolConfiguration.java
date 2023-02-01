package com.study.configuration;

import com.study.domain.model.Alunos;
import org.springframework.context.annotation.*;

import java.util.*;

@Configuration
public class SchoolConfiguration {

    @Bean
    public Map<Integer, Alunos> alunosRepository() {
        return new HashMap<>();
    }
}
