package com.study.configuration;

import com.study.domain.dto.*;
import org.springframework.context.annotation.*;

import java.util.*;

@Configuration
public class SchoolConfiguration {

    @Bean
    public Map<Integer, AlunoDTO> alunoRepository() {
        return new HashMap<>();
    }
    
    @Bean
    public Map<Integer, DisciplinaDTO> cursoRepository() {
        return new HashMap<>();
    }
    
    @Bean
    public Map<Integer, ProfessorDTO> professorRepository() {
        return new HashMap<>();
    }
}
