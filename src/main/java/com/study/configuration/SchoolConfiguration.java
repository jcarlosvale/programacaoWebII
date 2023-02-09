package com.study.configuration;

import com.study.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SchoolConfiguration {

    @Bean
    public Employee employee() {
        return new Employee("Jose");
    }
    
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
