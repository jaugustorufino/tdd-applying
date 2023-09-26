package org.example.junit5Tests;

import org.example.Boleto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Testes para a classe Boleto")
public class BoletoTest {
    Boleto boleto;

    @BeforeEach
    void init() {
        boleto = new Boleto("91232341231014", "12152023", 500.00);
    }

    @Test
    @DisplayName("Teste de criação de Boleto")
    public void testCriacaoBoleto() {
        assertEquals(boleto.getCodigo(), "91232341231014");
        assertEquals(boleto.getData(), "12152023");
        assertEquals(boleto.getValor(), 500.00);
    }
}
