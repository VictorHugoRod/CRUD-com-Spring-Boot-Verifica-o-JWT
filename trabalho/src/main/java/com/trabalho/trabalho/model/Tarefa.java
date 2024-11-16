package com.trabalho.trabalho.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Entity
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank(message = "O título é obrigatório")
    private String title;

    private String description;
    private LocalDateTime creationDate;
    private Status status = Status.AFazer;
    private Prioridade priority;

    @PrePersist
    protected void onCreate() {
        this.creationDate = LocalDateTime.now();
    }

    //getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Status getStatus() {
        return status;
    }

    public Prioridade getPriority() {
        return priority;
    }

    //setters


    public void setStatus(Status status) {
        this.status = status;
    }

    public void setTitle(@NotBlank(message = "O título é obrigatório") String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriority(Prioridade priority) {
        this.priority = priority;
    }
}
