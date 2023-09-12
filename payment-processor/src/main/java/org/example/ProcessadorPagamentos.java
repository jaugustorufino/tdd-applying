package org.example;

import java.util.List;

public class ProcessadorPagamentos {

    public void processaBoletos(Fatura fatura, List<Boleto> listaDeBoletos) {
        for (Boleto boleto : listaDeBoletos) {
            Pagamento pagamento = new Pagamento(boleto.getValor(), TipoPagamento.BOLETO);
            fatura.addPagamento(pagamento);
        }
        if (fatura.getValorAtual() <= 0.00) {
            fatura.setStatus(StatusFatura.PAGA);
        }
    }
}