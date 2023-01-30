package com.study.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.study.domain.dto.ProfessorDto;
import com.study.service.ProfessorService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/professores")
public class ProfessorController {

    
    @Autowired
    private final ProfessorService profService;
    
    @PostMapping(path="/save")
    public ResponseEntity<ProfessorDto> save(@RequestBody @Valid final ProfessorDto professor) {
        if (profService.save(professor)) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(professor);
        }
        else {
            return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .build();
        }
    }

    @GetMapping(path="/")
    public ResponseEntity<Collection<ProfessorDto>> getAll() {
        return ResponseEntity.ok( new ArrayList<ProfessorDto>(profService.getAll()));
        }


        @PostMapping(path="/addAll")
        public ResponseEntity<List<ProfessorDto>> addAll(@RequestBody @Valid final List<ProfessorDto> lstProf ) {
            try{
            profService.addAll(lstProf);
            return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(lstProf);
            }catch(Exception e){
                return ResponseEntity
                .status(HttpStatus.EXPECTATION_FAILED)
                .build();
            }
                
        }



}