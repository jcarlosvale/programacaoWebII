package com.study.configuration;

import com.study.dto.*;
import org.springframework.context.annotation.*;
import org.springframework.web.client.*;

import java.util.*;

@Configuration
public class SchoolConfiguration {

    @Bean
    public Map<Integer, CorDto> corRepository() {
        return new HashMap<>();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(); //REST client
    }
}
