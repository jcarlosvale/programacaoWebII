package com.study.configuration;

import com.study.*;
import com.study.dto.*;
import com.study.dto.v2.ProfessorDtoV2;
import org.springframework.context.annotation.*;

import java.util.*;

@Configuration
public class SchoolConfiguration {

    @Bean
    public Map<Integer, CorDto> corRepository() {
        return new HashMap<>();
    }

    @Bean
    public Employee employee() {
        return new Employee("Jose");
    }

    @Bean
    public Map<Integer, AlunoDto> alunoRepository() {
        return new HashMap<>();
    }

    @Bean
    public Map<Integer, DisciplinaDto> cursoRepository() {
        return new HashMap<>();
    }

    @Bean
    public Map<Integer, ProfessorDtoV2> professorRepository() {
        return new HashMap<>();
    }

}
