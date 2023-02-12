package com.study.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.study.dto.ProfessorRequest;
import com.study.dto.ProfessorResponse;
import com.study.mapper.ProfessorMapper;
import com.study.service.ProfessorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/professores")
public class ProfessorController {
    
    @Autowired
    private final ProfessorService profService;


    private final ProfessorMapper profMapper;
    
    @PostMapping(path="/save")
    public ResponseEntity<ProfessorResponse> save(@RequestBody @Valid final ProfessorRequest professor) {
        var response = profService.save(professor);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
    

    @GetMapping(path="/")
    public ResponseEntity<Collection<ProfessorResponse>> getAll() {
        return ResponseEntity.ok(new ArrayList<ProfessorResponse>(profService.getAll()));
        }

    @PostMapping(path="/addAll")
    public ResponseEntity<List<ProfessorResponse>> addAll(@RequestBody @Valid final List<ProfessorRequest> lstProf ) {
        try{
        profService.addAll(lstProf);
                
        return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(profMapper.requestToResponse(lstProf));
        }catch(Exception e){
            return ResponseEntity
            .status(HttpStatus.EXPECTATION_FAILED)
            .build();
        }
            
    }



}