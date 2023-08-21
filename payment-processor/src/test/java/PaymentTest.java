import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentTest {

    @Test
    public void testPagamento() {
        Payment pagamentoBoleto = new Payment(300.00, PaymentType.BOLETO);

        assertEquals(pagamentoBoleto.getValorPago(), 300);
        assertEquals(pagamentoBoleto.getTipo(), PaymentType.BOLETO);
    }
}
