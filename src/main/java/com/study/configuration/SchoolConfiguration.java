package com.study.configuration;

import com.study.dto.ColorDTO;
import com.study.dto.StudentRequestDTO;
import com.study.dto.SubjectRequestDTO;
import com.study.dto.TeacherRequestDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class SchoolConfiguration {
    @Bean
    public Map<Integer, StudentRequestDTO> studentRequestDtoMap() {
        return new HashMap<>();
    }

    @Bean
    public Map<Integer, TeacherRequestDTO> teacherRequestDTOMap() {
        return new HashMap<>();
    }

    @Bean
    public Map<Integer, SubjectRequestDTO> subjectRequestDTOMap() {
        return new HashMap<>();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
