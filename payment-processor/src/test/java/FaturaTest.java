import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FaturaTest {

    @Test
    public void testFatura() {
        Fatura fatura = new Fatura("José", 1500.00, "12312023");

        assertEquals(fatura.getNomeCliente(), "José");
        assertEquals(fatura.getValor(), 1500.00);
        assertEquals(fatura.getData(), "12312023");
    }

    @Test
    public void testAddPagamentoBoleto() {
        Fatura fatura = new Fatura("José", 1500.00, "12312023");
        Pagamento pagamentoBoleto = new Pagamento(300.00, TipoPagamento.BOLETO);
        fatura.addPagamento(pagamentoBoleto);

        assertEquals(fatura.getPagamentos().get(0), pagamentoBoleto);
    }
}
