package com.study.configuration;

import com.study.domain.dto.*;
import org.springframework.context.annotation.*;

import java.util.*;

@Configuration
public class SchoolConfiguration {
    @Bean
    public Map<Integer, AlunoRequestDto> alunoRequestDtoMap() {
        return new HashMap<>();
    }

    @Bean
    public Map<Integer, ProfessorRequestDto> professorRequestDtoMap() {
        return new HashMap<>();
    }

    @Bean
    public Map<Integer, CursoRequestDto> cursoRequestDtoMap() {
        return new HashMap<>();
    }
}
