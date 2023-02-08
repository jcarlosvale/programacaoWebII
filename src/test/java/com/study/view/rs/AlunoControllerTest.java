package com.study.view.rs;


import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.web.client.*;
import org.springframework.boot.test.web.server.LocalServerPort;

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
