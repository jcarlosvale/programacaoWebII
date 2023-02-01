package com.study.configuration;

import com.study.Employee;
import com.study.dto.AlunoDto;
import com.study.dto.DisciplinaDto;
import com.study.dto.ProfessorResponseDto;
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

}
