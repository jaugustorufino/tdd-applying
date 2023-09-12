package org.example.functionalTests;

import org.example.Boleto;
import org.example.Fatura;
import org.example.ProcessadorPagamentos;
import org.example.StatusFatura;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ParticaoPorEquivalenciaTest {

    private Fatura fatura;
    private List<Boleto> listaDeBoletos1;
    private List<Boleto> listaDeBoletos2;
    private List<Boleto> listaDeBoletos3;
    private ProcessadorPagamentos processador;

    @BeforeEach
    public void init() {
        fatura = new Fatura("José", 800.00, "12312023");
        listaDeBoletos1 = new ArrayList<>(Arrays.asList(
                new Boleto("91232341231031", "12152023", 500.00),
                new Boleto("82342341231032", "12102023", 299.99)
        ));
        listaDeBoletos2 = new ArrayList<>(Arrays.asList(
                new Boleto("91232341231014", "12122023", 400.00),
                new Boleto("82342341231012", "12132023", 400.00)
        ));
        listaDeBoletos3 = new ArrayList<>(Arrays.asList(
                new Boleto("91232341231014", "12052023", 500.00),
                new Boleto("82342341231011", "12102023", 600.00),
                new Boleto("82342341231025", "12202023", 100.00)
        ));
        processador = new ProcessadorPagamentos();
    }

    @Test
    public void testProcessaFaturaNaoPaga() {
        processador.processaBoletos(fatura, listaDeBoletos1);

        assertEquals(fatura.getStatus(), StatusFatura.NAO_PAGA);
    }

    @Test
    public void testProcessaFaturaPagaValorIgual() {
        processador.processaBoletos(fatura, listaDeBoletos2);

        assertEquals(fatura.getStatus(), StatusFatura.PAGA);
    }

    @Test
    public void testProcessaFaturaPagaAcimaValor() {
        processador.processaBoletos(fatura, listaDeBoletos3);

        assertEquals(fatura.getStatus(), StatusFatura.PAGA);
    }
}
