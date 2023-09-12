package org.example;

public class Boleto {
    private String codigo;
    private String data;
    private double valor;

    public Boleto(String codigo, String data, double valor) {
        this.codigo = codigo;
        this.data = data;
        this.valor = valor;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getData() {
        return data;
    }

    public double getValor() {
        return valor;
    }
}
