package com.study.view.rs;

import com.study.dto.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.*;

@RestController
@RequestMapping(path = "/cores")
@RequiredArgsConstructor
@Slf4j
public class CorController {

    private final Map<Integer, CorDto> repository;

    //@GetMapping  comentado para evitar conflito de endpoint com request param abaixo
    public ResponseEntity<List<CorDto>> getAll() {
        log.info("Listing cores");
        if (repository.isEmpty()) {
            return ResponseEntity.ok(List.of());
        }
        else {
            return ResponseEntity
                    .ok(new ArrayList<>(repository.values()));
        }
    }

    @GetMapping
    public ResponseEntity<List<CorDto>> getByPrefix(@RequestParam(value = "prefixo", required = false) final String prefixo) {
        log.info("Getting cor with prefix {}", prefixo);
        if(Objects.isNull(prefixo)) return getAll();
        var selectedCores =
                repository.values().stream()
                .filter(cor -> cor.getDescricao().startsWith(prefixo))
                .collect(Collectors.toList());
        return ResponseEntity
                .ok(selectedCores);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CorDto> getById(@PathVariable("id") final int id) {
        log.info("Getting cor {}", id);
        var cor = repository.get(id);
        if (Objects.isNull(cor)) {
            return ResponseEntity
                    .notFound()
                    .build();
        }
        else {
            return ResponseEntity
                    .ok(cor);
        }
    }

    @PostMapping
    public ResponseEntity<CorDto> save(@RequestBody final CorDto cor) {
        if (repository.containsKey(cor.getId())) {
            log.error("Collection contains id {}", cor.getId());
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .build();
        }
        else {
            repository.put(cor.getId(), cor);
            log.info("Inserted a new color {}", cor);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(cor);
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<CorDto> update(@PathVariable("id") final int id, @RequestBody final CorDto corDto) {
        log.info("Updating cor {}", id);
        if (!repository.containsKey(corDto.getId())) {
            return ResponseEntity
                    .notFound()
                    .build();
        }
        else {
            repository.put(id, corDto);
            return ResponseEntity
                    .ok(corDto);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") final int id) {
        log.info("Deleting cor {}", id);
        var cor = repository.get(id);
        if (Objects.isNull(cor)) {
            return ResponseEntity
                    .notFound()
                    .build();
        }
        else {
            repository.remove(id);
            return ResponseEntity
                    .noContent()
                    .build();
        }
    }
}
