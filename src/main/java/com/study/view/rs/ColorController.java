package com.study.view.rs;

import com.study.dto.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.*;

@RestController
@RequestMapping(path = "/v1/cores")
@RequiredArgsConstructor
@Slf4j
public class ColorController {
    private final Map<Integer, ColorDTO> repository;

    // @GetMapping  comentado para evitar conflito de endpoint com request param abaixo
    public ResponseEntity<List<ColorDTO>> getAll() {
        log.info("Listing colors");

        if (repository.isEmpty()) return ResponseEntity.ok(List.of());

        return ResponseEntity.ok(new ArrayList<>(repository.values()));
    }

    @GetMapping
    public ResponseEntity<List<ColorDTO>> getByPrefix(@RequestParam(value = "prefixo", required = false) final String prefix) {
        log.info("Getting color with prefix {}", prefix);

        if(Objects.isNull(prefix)) return getAll();

        var selectedCores =
                repository.values().stream()
                .filter(color -> color.getDescription().startsWith(prefix))
                .collect(Collectors.toList());

        return ResponseEntity.ok(selectedCores);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ColorDTO> getById(@PathVariable("id") final Integer id) {
        log.info("Getting color {}", id);

        var color = repository.get(id);

        if (Objects.isNull(color)) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(color);
    }

    @PostMapping
    public ResponseEntity<ColorDTO> save(@RequestBody final ColorDTO color) {
        if (repository.containsKey(color.getId())) {
            log.error("Collection contains id {}", color.getId());

            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        repository.put(color.getId(), color);

        log.info("Inserted a new color {}", color);

        return ResponseEntity.status(HttpStatus.CREATED).body(color);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ColorDTO> update(@PathVariable("id") final Integer id, @RequestBody final ColorDTO color) {
        log.info("Updating color {}", id);

        if (!repository.containsKey(color.getId())) return ResponseEntity.notFound().build();

        repository.put(id, color);

        return ResponseEntity.ok(color);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") final Integer id) {
        log.info("Deleting color {}", id);

        var color = repository.get(id);

        if (Objects.isNull(color)) return ResponseEntity.notFound().build();

        repository.remove(id);

        return ResponseEntity.noContent().build();
    }
}
