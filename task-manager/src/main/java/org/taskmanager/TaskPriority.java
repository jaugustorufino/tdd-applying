package org.taskmanager;

public enum TaskPriority {
    HIGH(3),
    MEDIUM(2),
    LOW(1);

    private final int valor;

    TaskPriority(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
