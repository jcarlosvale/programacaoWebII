package com.study.view.rs;

import com.study.domain.dto.AlunoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(path = "/alunos")
@RequiredArgsConstructor
@Slf4j
public class AlunoController {

    private final Map<Integer, AlunoDto> repository;

//    //@GetMapping  comentado para evitar conflito de endpoint com request param abaixo
//    public ResponseEntity<List<CorDto>> getAll() {
//        log.info("Listing cores");
//        if (repository.isEmpty()) {
//            return ResponseEntity.ok(List.of());
//        }
//        else {
//            return ResponseEntity
//                    .ok(new ArrayList<>(repository.values()));
//        }
//    }
//
//    @GetMapping
//    public ResponseEntity<List<CorDto>> getByPrefix(@RequestParam(value = "prefixo", required = false) final String prefixo) {
//        log.info("Getting cor with prefix {}", prefixo);
//        if(Objects.isNull(prefixo)) return getAll();
//        var selectedCores =
//                repository.values().stream()
//                        .filter(cor -> cor.getDescricao().startsWith(prefixo))
//                        .collect(Collectors.toList());
//        return ResponseEntity
//                .ok(selectedCores);
//    }
//
//    @GetMapping(path = "/{id}")
//    public ResponseEntity<CorDto> getById(@PathVariable("id") final int id) {
//        log.info("Getting cor {}", id);
//        var cor = repository.get(id);
//        if (Objects.isNull(cor)) {
//            return ResponseEntity
//                    .notFound()
//                    .build();
//        }
//        else {
//            return ResponseEntity
//                    .ok(cor);
//        }
//    }
//
    @PostMapping
    public ResponseEntity<AlunoDto> save(@RequestBody final AlunoDto aluno) {

        if(Objects.isNull(aluno.getNome()) || Objects.isNull(aluno.getId())){
            log.error("Atributos de aluno são nulos", aluno.getId());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }


        if (repository.containsKey(aluno.getId())) {
            log.error("Collection contains id {}", aluno.getId());
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .build();
        }
        else {
            repository.put(aluno.getId(), aluno);
            log.info("Inserted a new aluno {}", aluno);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(aluno);
        }
    }
//
//    @PutMapping(path = "/{id}")
//    public ResponseEntity<CorDto> update(@PathVariable("id") final int id, @RequestBody final CorDto corDto) {
//        log.info("Updating cor {}", id);
//        if (!repository.containsKey(corDto.getId())) {
//            return ResponseEntity
//                    .notFound()
//                    .build();
//        }
//        else {
//            repository.put(id, corDto);
//            return ResponseEntity
//                    .ok(corDto);
//        }
//    }
//
//    @DeleteMapping(path = "/{id}")
//    public ResponseEntity<Void> delete(@PathVariable("id") final int id) {
//        log.info("Deleting cor {}", id);
//        var cor = repository.get(id);
//        if (Objects.isNull(cor)) {
//            return ResponseEntity
//                    .notFound()
//                    .build();
//        }
//        else {
//            repository.remove(id);
//            return ResponseEntity
//                    .noContent()
//                    .build();
//        }
//    }
}
