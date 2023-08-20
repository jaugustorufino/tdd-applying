import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentSlipTest {

    @Test
    public void testBoleto() {
        PaymentSlip boleto = new PaymentSlip("91232341231014", "12152023", 500.00);

        assertEquals(boleto.getCodigo(), "91232341231014");
        assertEquals(boleto.getData(), "12152023");
        assertEquals(boleto.getValor(), 500.00);
    }
}