package com.study.view.rs;

import com.study.domain.model.Alunos;
import com.study.domain.repositories.AlunosRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AlunosControllerTest {

    @Autowired
    private AlunosRepository repository;

    @Value("${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String URL;

    @BeforeEach
    void setup() {
        URL = "http://localhost:" + port + "/alunos";
        repository.save(Alunos.builder().id(1L).nome("Miguel").build());
        repository.save(Alunos.builder().id(2L).nome("Marcio").build());
        repository.save(Alunos.builder().id(3L).nome("Carla").build());
        repository.save(Alunos.builder().id(4L).nome("Caroline").build());
    }

    @Test
    void getAll() {
        //GIVEN
        var initialCounter = repository.count();

        //WHEN
        var response = restTemplate.getForEntity(URL, Alunos.class);

        //THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(response.getBody())).isEqualTo(response.getBody());
//        assertThat(repository).hasSize(initialCounter);
    }

    @Test
    void getAll_Empty() {

        //WHEN
        var response = restTemplate.getForEntity(URL, Alunos.class);

        //THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNull();
        assertThat(repository).isNull();
    }

    @Test
    void getById() {
        //GIVEN
        var expected = repository.findAll().stream().findFirst();
        var id = expected.get().getId();

        //WHEN
        var response = restTemplate.getForEntity(URL + "/{id}", Alunos.class, id);

        //THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(response.getBody())).isEqualTo(response.getBody());
    }

    @Test
    void getById_NotFound() {

        //WHEN
        var response = restTemplate.getForEntity(URL + "/{id}", Alunos.class, 999);

        //THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void save() {
        //GIVEN
        var newDto =
                Alunos.builder()
                        .id(9999L)
                        .nome("nome")
                        .build();

        //WHEN
        var response = restTemplate.postForEntity(URL, newDto, Alunos.class);

        //THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        var body = Objects.requireNonNull(response.getBody());
        assertThat(body).isEqualTo(newDto);
    }

    @Test
    void save_Exists() {
        //GIVEN
        var dto = repository.findAll().stream().findFirst();

        //WHEN
        var response = restTemplate.postForEntity(URL, dto, Alunos.class);

        //THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void update() {
        //GIVEN
        var dto = repository.findAll().stream().findFirst().get();
        dto.setNome("novo nome");

        //WHEN
        var response = restTemplate.exchange(URL + "/{id}", HttpMethod.PUT, new HttpEntity<>(dto),
                Alunos.class, dto.getId());

        //THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(dto);
    }

    @Test
    void update_NotFound() {
        //GIVEN
        var request = Alunos.builder().build();

        //WHEN
        var response = restTemplate.exchange(URL + "/{id}", HttpMethod.PUT,
                new HttpEntity<>(request), Alunos.class, 999);

        //THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void delete() {
        //GIVEN
        var dto = repository.findAll().stream().findFirst();

        //WHEN
        var response = restTemplate.exchange(URL + "/{id}", HttpMethod.DELETE, null, Void.class, dto.get().getId());

        //THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    void delete_NotFound() {

        //WHEN
        var response = restTemplate.exchange(URL + "/{id}", HttpMethod.DELETE, null, Void.class, 999);

        //THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}