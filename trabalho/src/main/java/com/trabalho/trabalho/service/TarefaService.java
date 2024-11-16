package com.trabalho.trabalho.service;


import com.trabalho.trabalho.model.Status;
import com.trabalho.trabalho.model.Tarefa;
import com.trabalho.trabalho.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    //metodo para add uma tarefa
    public Tarefa addTask(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    //metodo para listar as tarefas por coluna
    public Map<String, List<Tarefa>> listTasksForStatus(){
        List<Tarefa> tasks = tarefaRepository.findAll();

        Map<String, List<Tarefa>> tasksForStatus = tasks.stream().collect(Collectors.groupingBy(t -> t.getStatus().toString()));

        List<String> order = List.of("AFazer", "EmProgresso", "Concluido");

        Map<String, List<Tarefa>> orderedTasks = new LinkedHashMap<>();

        for(String status : order){
            orderedTasks.put(status, tasksForStatus.getOrDefault(status, List.of()));
        }

        return orderedTasks;

    }

    //metodo para atualizar o status da tarefa
    public Tarefa updateStatus(int id) {
        Tarefa tarefa = tarefaRepository.findById(id).orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));

        switch (tarefa.getStatus()) {
            case AFazer:
                tarefa.setStatus(Status.EmProgresso);
                break;
            case EmProgresso:
                tarefa.setStatus(Status.Concluido);
                break;
            case Concluido:
                throw new RuntimeException("A tarefa já foi concluída");
        }

        return tarefaRepository.save(tarefa);

    }

    //metodo para atualizar uma tarefa
    public Tarefa updateTask(int id, Tarefa updatedTask) {
        Tarefa existentTask = tarefaRepository.findById(id).orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));

        existentTask.setTitle(updatedTask.getTitle());
        existentTask.setDescription(updatedTask.getDescription());
        existentTask.setPriority(updatedTask.getPriority());

        return tarefaRepository.save(existentTask);
    }

    //metodo para excluir uma tarefa
    public Tarefa deleteTask(int id) {
        Tarefa existentTask = tarefaRepository.findById(id).orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));

        tarefaRepository.delete(existentTask);

        return existentTask;
    }


    //FUNCIONALIDADES EXTRAS

    public Map<String, List<Tarefa>> listTasksForStatusAndPriority() {
        List<Tarefa> tasks = tarefaRepository.findAll();

        List<String> statusOrder = List.of("AFazer", "EmProgresso", "Concluido");

        return tasks.stream()
                .collect(Collectors.groupingBy(
                        t -> t.getStatus().toString(),
                        LinkedHashMap::new,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> {
                                    list.sort((t1, t2) -> {
                                        int statusOrderComparison = Integer.compare(statusOrder.indexOf(t1.getStatus().toString()), statusOrder.indexOf(t2.getStatus().toString()));
                                        if (statusOrderComparison != 0) {
                                            return statusOrderComparison;
                                        }
                                        return t1.getPriority().compareTo(t2.getPriority());
                                    });
                                    return list;
                                }
                        )
                ));
    }

    public List<Tarefa> listTasksForPriority(int priority) {
        List<Tarefa> tasks = tarefaRepository.findAll();

        return tasks.stream()
                .filter(t -> t.getPriority().getValue() == priority)
                .collect(Collectors.toList());
    }

}
