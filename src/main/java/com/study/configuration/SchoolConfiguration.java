package com.study.configuration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.study.dto.response.AlunoResponse;

@Configuration
public class SchoolConfiguration {

    @Bean
    public Map<Integer, AlunoResponse> corRepository() {
        return new HashMap<>();
    }
}