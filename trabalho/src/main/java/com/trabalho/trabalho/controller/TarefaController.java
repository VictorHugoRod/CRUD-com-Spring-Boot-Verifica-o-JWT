package com.trabalho.trabalho.controller;

import com.trabalho.trabalho.model.Tarefa;
import com.trabalho.trabalho.service.TarefaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchTransactionManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tasks")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @PostMapping
    public Tarefa add(@Valid @RequestBody Tarefa tarefa){
        return tarefaService.addTask(tarefa);
    }

    @GetMapping("/tasks")
    public Map<String, List<Tarefa>> listTasksForStatus() {
        return tarefaService.listTasksForStatus();
    }

    @PutMapping("/{id}/move")
    public Tarefa uptadeStatus(@PathVariable int id){
        return tarefaService.updateStatus(id);
    }

    @PutMapping("/{id}")
    public Tarefa updateTask(@PathVariable int id, @RequestBody Tarefa tarefa){
        return tarefaService.updateTask(id, tarefa);
    }

    @DeleteMapping("/{id}")
    public Tarefa delete(@PathVariable int id){
        return tarefaService.deleteTask(id);
    }


    @GetMapping("/tasks/priority")
    public Map<String, List<Tarefa>> listTasksForStatusAndPriority() {
        return tarefaService.listTasksForStatusAndPriority();
    }

    @GetMapping("/tasks/priority/{priority}")
    public List<Tarefa> listTasksForPriority(@PathVariable int priority) {
        return tarefaService.listTasksForPriority(priority);
    }
}
