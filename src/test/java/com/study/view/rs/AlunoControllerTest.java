package com.study.view.rs;


import com.study.domain.dto.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.web.client.*;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.util.Assert;

import java.util.*;
import java.util.stream.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AlunoControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String url;

    @BeforeEach
    void setup(){
        url = "http://localhost:" + port + "/alunos";

    }
   /* @Test
    public void testaSaveSucesso(){
        ResponseEntity response = alunoController.save(new AlunoDto(1, "Luiz"));

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }**/
}
