import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InvoiceTest {

    @Test
    public void testFatura() {
        Invoice fatura = new Invoice("José", 1500.00, "12312023");

        assertEquals(fatura.getNomeCliente(), "José");
        assertEquals(fatura.getValor(), 1500.00);
        assertEquals(fatura.getData(), "12312023");
    }
}
