package com.study.configuration;

import com.study.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchoolConfiguration {

    @Bean
    public Employee employee() {
        return new Employee("Jose");
    }

}
