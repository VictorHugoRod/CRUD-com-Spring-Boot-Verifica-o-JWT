package com.trabalho.trabalho.repository;

import com.trabalho.trabalho.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {
}
