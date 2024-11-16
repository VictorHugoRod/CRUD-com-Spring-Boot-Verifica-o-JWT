package com.trabalho.trabalho.model;

public enum Prioridade {
    baixa(1),
    media(2),
    alta(3);

    private final int value;

    Prioridade(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}