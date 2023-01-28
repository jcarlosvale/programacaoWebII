package com.study.view.rs;

import com.study.domain.dto.AlunoDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AlunoControllerTest {

    @Autowired
    private Map<Integer, AlunoDto> repository;

    @Value("{local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    private String URL;

    @BeforeEach
    public void setup() {

        URL = "http://localhost:" + port + "/cores";

        repository.put(1, AlunoDto.builder().id(1).name("Robson").build());
        repository.put(2, AlunoDto.builder().id(2).name("Cleyton").build());
        repository.put(3, AlunoDto.builder().id(3).name("Harry").build());
        repository.put(4, AlunoDto.builder().id(4).name("Aron").build());
        repository.put(5, AlunoDto.builder().id(5).name("First").build());

    }

    @AfterEach
    public void tearDown() {
        repository.clear();
    }

    @Test
    public void test(){

    }

}
