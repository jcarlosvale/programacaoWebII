package com.study.view.rs;

import com.study.domain.model.Alunos;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.web.client.*;
import org.springframework.http.*;

import java.util.*;
import java.util.stream.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AlunosControllerTest {

    @Autowired
    private Map<Integer, Alunos> repository;

    @Value("${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String URL;

    @BeforeEach
    void setup() {
        URL = "http://localhost:" + port + "/alunos";
        repository.put(1, Alunos.builder().id(1).nome("Miguel").build());
        repository.put(2, Alunos.builder().id(2).nome("Marcio").build());
        repository.put(3, Alunos.builder().id(3).nome("Carla").build());
        repository.put(4, Alunos.builder().id(4).nome("Caroline").build());
    }

    @AfterEach
    void tearDown() {
        repository.clear();
    }

    @Test
    void getAll() {
        //GIVEN
        var initialCounter = repository.size();

        //WHEN
        var response = restTemplate.getForEntity(URL, Alunos[].class);

        //THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(response.getBody())).hasSize(repository.size());
        assertThat(repository).hasSize(initialCounter);
    }

    @Test
    void getAll_Empty() {
        //GIVEN
        repository.clear();

        //WHEN
        var response = restTemplate.getForEntity(URL, Alunos[].class);

        //THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEmpty();
        assertThat(repository).isEmpty();
    }

    @ParameterizedTest
    @MethodSource("dataByPrefix")
    void getByPrefix(final String prefix, final int counter) {
        //GIVEN
        URL = URL + "?prefixo={prefixo}";
        var initialCounter = repository.size();

        //WHEN
        var response = restTemplate.getForEntity(URL, Alunos[].class, prefix);

        //THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(response.getBody())).hasSize(counter);
        assertThat(repository).hasSize(initialCounter);
    }

    static Stream<Arguments> dataByPrefix() {
        return Stream.of(
                arguments("M", 0),
                arguments("Ama", 1),
                arguments("Car", 2),
                arguments(null, 4)
        );
    }

    @Test
    void getById() {
        //GIVEN
        var expected = repository.values().stream().findFirst().get();
        var id = expected.getId();
        var initialCounter = repository.size();

        //WHEN
        var response = restTemplate.getForEntity(URL+ "/{id}", Alunos.class, id);

        //THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(response.getBody())).isEqualTo(expected);
        assertThat(repository).hasSize(initialCounter);
    }

    @Test
    void getById_NotFound() {
        //GIVEN
        var initialCounter = repository.size();

        //WHEN
        var response = restTemplate.getForEntity(URL+ "/{id}", Alunos.class, 999);

        //THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(repository).hasSize(initialCounter);
    }

    @Test
    void save() {
        //GIVEN
        var newDto =
                Alunos.builder()
                .id(9999)
                .nome("nome")
                .build();
        var initialCounter = repository.size();

        //WHEN
        var response = restTemplate.postForEntity(URL, newDto, Alunos.class);

        //THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        var body = Objects.requireNonNull(response.getBody());
        assertThat(body).isEqualTo(newDto);
        assertThat(repository).hasSize(initialCounter+1);
    }

    @Test
    void save_Exists() {
        //GIVEN
        var dto = repository.values().stream().findFirst().get();
        var initialCounter = repository.size();

        //WHEN
        var response = restTemplate.postForEntity(URL, dto, Alunos.class);

        //THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
        assertThat(repository).hasSize(initialCounter);
    }

    @Test
    void update() {
        //GIVEN
        var dto = repository.values().stream().findFirst().get();
        dto.setNome("novo nome");
        var initialCounter = repository.size();

        //WHEN
        var response = restTemplate.exchange(URL+"/{id}", HttpMethod.PUT, new HttpEntity<>(dto),
                Alunos.class, dto.getId());

        //THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(dto);
        assertThat(repository).hasSize(initialCounter);
    }

    @Test
    void update_NotFound() {
        //GIVEN
        var request = Alunos.builder().build();
        var initialCounter = repository.size();

        //WHEN
        var response = restTemplate.exchange(URL+"/{id}", HttpMethod.PUT,
                new HttpEntity<>(request), Alunos.class, 999);

        //THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(repository).hasSize(initialCounter);
    }

    @Test
    void delete() {
        //GIVEN
        var dto = repository.values().stream().findFirst().get();
        var initialCounter = repository.size();

        //WHEN
        var response = restTemplate.exchange(URL+"/{id}", HttpMethod.DELETE, null, Void.class, dto.getId());

        //THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(repository).hasSize(initialCounter-1);
    }

    @Test
    void delete_NotFound() {
        //GIVEN
        var initialCounter = repository.size();

        //WHEN
        var response = restTemplate.exchange(URL+"/{id}", HttpMethod.DELETE, null, Void.class, 999);

        //THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(repository).hasSize(initialCounter);
    }
}