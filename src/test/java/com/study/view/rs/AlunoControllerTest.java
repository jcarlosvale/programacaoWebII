package com.study.view.rs;

import com.study.domain.dto.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.web.client.*;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.*;
import java.util.stream.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AlunoControllerTest {

    @Autowired
    private Map<Integer, AlunoDto> repository;

    //@Value("${local.server.port}")
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String URL;

    @BeforeEach
    void setup() {
        URL = "http://localhost:" + port + "/";
        repository.put(1, AlunoDto.builder().id(1).nome("Juscelino").build());
        repository.put(2, AlunoDto.builder().id(2).nome("Vinicius").build());
        repository.put(3, AlunoDto.builder().id(3).nome("Eric").build());
        repository.put(4, AlunoDto.builder().id(4).nome("Julho").build());
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
        var response = restTemplate.getForEntity(URL, AlunoDto[].class);

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
        var response = restTemplate.getForEntity(URL, AlunoDto[].class);

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
        var response = restTemplate.getForEntity(URL, AlunoDto[].class, prefix);

        //THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(response.getBody())).hasSize(counter);
        assertThat(repository).hasSize(initialCounter);
    }

    static Stream<Arguments> dataByPrefix() {
        return Stream.of(
                arguments("Jus", 0),
                arguments("Vin", 1),
                arguments("Er", 2),
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
        var response = restTemplate.getForEntity(URL+ "/{id}", AlunoDto.class, id);

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
        var response = restTemplate.getForEntity(URL+ "/{id}", AlunoDto.class, 999);

        //THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(repository).hasSize(initialCounter);
    }

    @Test
    void save() {
        //GIVEN
        var newDto =
                AlunoDto.builder()
                .id(9999)
                .nome("descricao")
                .build();
        var initialCounter = repository.size();

        //WHEN
        var response = restTemplate.postForEntity(URL, newDto, AlunoDto.class);

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
        var response = restTemplate.postForEntity(URL, dto, AlunoDto.class);

        //THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
        assertThat(repository).hasSize(initialCounter);
    }

    @Test
    void update() {
        //GIVEN
        var dto = repository.values().stream().findFirst().get();
        dto.setNome("nova descricao");
        var initialCounter = repository.size();

        //WHEN
        var response = restTemplate.exchange(URL+"/{id}", HttpMethod.PUT, new HttpEntity<>(dto),
        AlunoDto.class, dto.getId());

        //THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(dto);
        assertThat(repository).hasSize(initialCounter);
    }

    @Test
    void update_NotFound() {
        //GIVEN
        var request = AlunoDto.builder().build();
        var initialCounter = repository.size();

        //WHEN
        var response = restTemplate.exchange(URL+"/{id}", HttpMethod.PUT,
                new HttpEntity<>(request), AlunoDto.class, 999);

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