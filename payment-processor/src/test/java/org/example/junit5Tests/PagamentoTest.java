package org.example.junit5Tests;

import org.example.Fatura;
import org.example.Pagamento;
import org.example.TipoPagamento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Testes para a classe Pagamento")
public class PagamentoTest {
    Pagamento pagamentoBoleto;

    @BeforeEach
    void init() {
        pagamentoBoleto = new Pagamento(300.00, TipoPagamento.BOLETO);
    }


    @Test
    @DisplayName("Teste de criação de Pagamento")
    void testCriacaoPagamento() {
        assertEquals(pagamentoBoleto.getValorPago(), 300.00);
        assertEquals(pagamentoBoleto.getTipo(), TipoPagamento.BOLETO);
    }
}
