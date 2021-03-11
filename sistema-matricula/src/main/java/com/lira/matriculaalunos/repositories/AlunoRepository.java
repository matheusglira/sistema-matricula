package com.lira.matriculaalunos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lira.matriculaalunos.models.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer>{

}
