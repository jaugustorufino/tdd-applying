package org.example.junit5Tests;

import org.example.Boleto;
import org.example.Fatura;
import org.example.ProcessadorPagamentos;
import org.example.StatusFatura;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("Testes para o sistema Boleto usando Tabelas de Decisão")
public class TabelasDeDecisaoTest {

    private Fatura fatura;
    private List<Boleto> listaDeBoletos1;
    private List<Boleto> listaDeBoletos2;
    private ProcessadorPagamentos processador;

    @BeforeEach
    public void init() {
        fatura = new Fatura("José", 1000.00, "12312023");
        listaDeBoletos1 = new ArrayList<>(Arrays.asList(
                new Boleto("91232341231031", "12152023", 600.00),
                new Boleto("82342341231032", "12102023", 399.99)
        ));
        listaDeBoletos2 = new ArrayList<>(Arrays.asList(
                new Boleto("91232341231014", "12122023", 600.00),
                new Boleto("82342341231012", "12132023", 400.00)
        ));
        processador = new ProcessadorPagamentos();
    }

    @Test
    @DisplayName("Teste Processa Fatura Não Paga")
    public void testProcessaFaturaNaoPaga() {
        processador.processaBoletos(fatura, listaDeBoletos1);

        assertEquals(StatusFatura.NAO_PAGA, fatura.getStatus());
    }

    @Test
    @DisplayName("Teste Processa Fatura Paga")
    public void testProcessaFaturaPaga() {
        processador.processaBoletos(fatura, listaDeBoletos2);

        assertEquals(StatusFatura.PAGA, fatura.getStatus());
    }

}
