package com.study.repository;


import com.study.model.*;
import org.springframework.data.jpa.repository.*;
import java.util.*;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

    List<Aluno> findAlunosByTutor(final Professor tutor);


}