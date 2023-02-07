package com.study.configuration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.study.domain.dto.AlunoRequestDto;
import com.study.domain.dto.CursoRequestDto;
import com.study.domain.dto.ProfessorRequestDto;

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
    
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(); //REST client
    }
}
