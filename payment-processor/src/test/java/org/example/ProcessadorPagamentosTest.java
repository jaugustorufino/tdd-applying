package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ProcessadorPagamentosTest {

    private Fatura fatura;
    private List<Boleto> listaDeBoletosFaturaPaga;
    private List<Boleto> listaDeBoletosFaturaNaoPaga;
    private ProcessadorPagamentos processador;

    @BeforeEach
    public void init() {
        fatura = new Fatura("José", 1500.00, "12312023");
        listaDeBoletosFaturaPaga = new ArrayList<>(Arrays.asList(
                new Boleto("91232341231014", "12152023", 500.00),
                new Boleto("82342341231012", "12102023", 400.00),
                new Boleto("76242341231011", "12052023", 600.00)
        ));
        listaDeBoletosFaturaNaoPaga = new ArrayList<>(Arrays.asList(
                new Boleto("91232341231014", "12152023", 500.00),
                new Boleto("82342341231012", "12102023", 400.00)
        ));
        processador = new ProcessadorPagamentos();
    }

    @Test
    public void testProcessaFaturaPaga() {
        processador.processaBoletos(fatura, listaDeBoletosFaturaPaga);

        assertEquals(fatura.getStatus(), StatusFatura.PAGA);
        assertEquals(fatura.getPagamentos().size(), 3);
    }

    @Test
    public void testProcessaFaturaNaoPaga() {
        processador.processaBoletos(fatura, listaDeBoletosFaturaNaoPaga);

        assertEquals(fatura.getStatus(), StatusFatura.NAO_PAGA);
        assertEquals(fatura.getPagamentos().size(), 2);
    }

    @Test
    public void testCriacaoPagamento() {
        processador.processaBoletos(fatura, listaDeBoletosFaturaNaoPaga);

        assertEquals(fatura.getPagamentos().get(0).getValorPago(), 500.00);
        assertEquals(fatura.getPagamentos().get(0).getTipo(), TipoPagamento.BOLETO);
        assertEquals(fatura.getStatus(), StatusFatura.NAO_PAGA);
    }
}
