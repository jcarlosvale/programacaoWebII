package com.study.configuration;

import com.study.Employee;
import com.study.dto.AlunoDto;
import com.study.dto.DisciplinaDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class SchoolConfiguration {

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
    public Map<Integer, ProfessorDto> professorRepository() {
        return new HashMap<>();
    }

}
