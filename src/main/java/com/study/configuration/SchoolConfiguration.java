package com.study.configuration;

import com.study.domain.dto.*;
import org.springframework.context.annotation.*;

import java.util.*;

@Configuration
public class SchoolConfiguration {

    @Bean
    public Map<Integer, AlunoDto> alunoRepository() {
        return new HashMap<>();
    }

    @Bean
    public Map<Integer, ProfessorDto> professorRepository() {
        return new HashMap<>();
    }

    @Bean
    public Map<Integer, CursoDto> cursoRepository() {
        return new HashMap<>();
    }
}
