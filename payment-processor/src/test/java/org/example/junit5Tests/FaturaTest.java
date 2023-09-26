package org.example.junit5Tests;

import org.example.Fatura;
import org.example.Pagamento;
import org.example.TipoPagamento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Testes para a classe Fatura")
public class FaturaTest {
    Fatura fatura;
    Pagamento pagamentoBoleto;

    @BeforeEach
    void init() {
        fatura = new Fatura("José", 1500.00, "12312023");
        pagamentoBoleto = new Pagamento(300.00, TipoPagamento.BOLETO);
    }

    @Test
    @DisplayName("Teste de criação de Fatura")
    void testCriacaoFatura() {
        assertEquals(fatura.getNomeCliente(), "José");
        assertEquals(fatura.getValorTotal(), 1500.00);
        assertEquals(fatura.getData(), "12312023");
    }

    @Test
    @DisplayName("Teste de adição de pagamento via Boleto")
    void testAddPagamentoBoleto() {
        fatura.addPagamento(pagamentoBoleto);

        assertEquals(fatura.getPagamentos().get(0), pagamentoBoleto);
    }
}
