import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class PaymentProcessorTest {

    @Test
    public void testFatura() {
        Invoice fatura = new Invoice("José", 1500.00, "12312023");

        assertEquals(fatura.getNomeDoCliente(), "José");
        assertEquals(fatura.getValor(), 1500.00);
        assertEquals(fatura.getData(), "12312023");
    }
}
