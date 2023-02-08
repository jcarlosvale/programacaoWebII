package com.study.view.rs;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AlunoControllerTest {

    @LocalServerPort //porta randomica
    private int port;

    @Autowired
    private TestRestTemplate restTemplate; //é como se fosse o browser

    private String url;

    @BeforeEach
    void setup(){
        url = "http://localhost:" + port + "/alunos";

    }





}