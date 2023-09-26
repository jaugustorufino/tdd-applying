package org.example.junit5Tests;

import org.example.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("Testes para o sistema Boleto como um todo")
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
    @DisplayName("Teste Processa Fatura Paga")
    public void testProcessaFaturaPaga() {
        processador.processaBoletos(fatura, listaDeBoletosFaturaPaga);

        assertEquals(StatusFatura.PAGA, fatura.getStatus());
        assertEquals(3, fatura.getPagamentos().size());
    }

    @Test
    @DisplayName("Teste Processa Fatura Não Paga")
    public void testProcessaFaturaNaoPaga() {
        processador.processaBoletos(fatura, listaDeBoletosFaturaNaoPaga);

        assertEquals(StatusFatura.NAO_PAGA, fatura.getStatus());
        assertEquals(2, fatura.getPagamentos().size());
    }

    @Test
    @DisplayName("Teste Criacao Pagamento")
    public void testCriacaoPagamento() {
        processador.processaBoletos(fatura, listaDeBoletosFaturaNaoPaga);

        assertEquals(500.00, fatura.getPagamentos().get(0).getValorPago());
        assertEquals(TipoPagamento.BOLETO, fatura.getPagamentos().get(0).getTipo());
        assertEquals(StatusFatura.NAO_PAGA, fatura.getStatus());
    }

}
