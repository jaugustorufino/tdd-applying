package org.example;

import org.example.Pagamento;
import org.example.TipoPagamento;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PagamentoTest {

    @Test
    public void testCriacaoPagamento() {
        Pagamento pagamentoBoleto = new Pagamento(300.00, TipoPagamento.BOLETO);

        assertEquals(pagamentoBoleto.getValorPago(), 300);
        assertEquals(pagamentoBoleto.getTipo(), TipoPagamento.BOLETO);
    }
}
