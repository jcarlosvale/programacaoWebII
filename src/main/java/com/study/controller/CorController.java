package com.study.controller;

import com.study.dto.Cor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/cores")
public class CorController {

    //CRUD <--> POST, GET, PUT, DELETE, PATCH, HEAD,.....
    private List<Cor> lista = new ArrayList<>();

    //CREATE
    @PostMapping
    public void save(@RequestBody final Cor cor) {
        lista.add(cor);
    }

    //READ
    @GetMapping
    public List<Cor> getAll() {
        return lista;
    }

    //UPDATE
    @PutMapping
    public String update(String cor) {
        return "PUT";
    }

    //DELETE
    @DeleteMapping
    public void delete(String cor) {

    }
}
