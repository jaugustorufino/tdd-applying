import java.util.List;

public class PaymentProcessor {

    public void processaBoletos(Invoice fatura, List<PaymentSlip> listaDeBoletos) {
        for (PaymentSlip boleto : listaDeBoletos) {
            Payment pagamento = new Payment(boleto.getValor(), PaymentType.BOLETO);
            fatura.addPagamento(pagamento);
        }
        if (fatura.getValorAtual() <= 0.00) {
            fatura.setStatus(InvoiceStatus.PAGA);
        }
    }
}