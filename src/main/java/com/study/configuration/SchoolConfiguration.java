package com.study.configuration;

import com.study.domain.dto.*;
import com.study.dto.response.CorDto;
import org.springframework.context.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Configuration
public class SchoolConfiguration {

    @Bean
    public Map<Integer, CorDto> corRepository() {
        return new HashMap<>();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
