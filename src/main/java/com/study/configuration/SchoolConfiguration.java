package com.study.configuration;

import com.study.domain.dto.*;
import org.springframework.context.annotation.*;

import java.util.*;

@Configuration
public class SchoolConfiguration {

    @Bean
    public Map<Integer, AlunosDto> alunosRepository() {
        return new HashMap<>();
    }
}
