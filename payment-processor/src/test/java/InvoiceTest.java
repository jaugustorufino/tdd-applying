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

    @Test
    public void testAddPagamentoBoleto() {
        Invoice fatura = new Invoice("José", 1500.00, "12312023");
        Payment pagamentoBoleto = new Payment(300.00, PaymentType.BOLETO);
        fatura.addPagamento(pagamentoBoleto);

        assertEquals(fatura.getPagamentos().get(0), pagamentoBoleto);
    }
}
