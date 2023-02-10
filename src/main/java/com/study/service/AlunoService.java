package com.study.service;

import java.util.List;

import com.study.dto.AlunoResponse;


public interface AlunoService {
    public List<AlunoResponse> getAll();
    public List<AlunoResponse> getByPrefix(final String prefixo);
    public AlunoResponse getById(final int id);
    public AlunoResponse save(final AlunoResponse aluno);
    public AlunoResponse update(final int id, final AlunoResponse alunoResponse);
    public void delete(final int id);
    public AlunoResponse updateTutor(final Integer idAluno, final Integer idTutor);
}
