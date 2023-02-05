package com.study.configuration;
/* 
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.study.domain.dto.ProfessorDto;
*/

import org.springframework.context.annotation.Configuration;

@Configuration
@EnableJpaRepositories("com.study.Repository")
public class ProfessorConfiguration {
 
/* @Bean
    public Map<Integer, ProfessorDto> profRepository(){
        return new HashMap<>();
    }
*/
}
