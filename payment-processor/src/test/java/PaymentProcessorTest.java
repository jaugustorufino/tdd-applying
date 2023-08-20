import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class PaymentProcessorTest {

    private Invoice faturaPaga;
    private Invoice faturaNaoPaga;
    private List<PaymentSlip> listaDeBoletosFaturaPaga;
    private List<PaymentSlip> listaDeBoletosFaturaNaoPaga;
    private PaymentProcessor processador;

    @BeforeEach
    public void init() {
        faturaPaga = new Invoice("José", 1500.00, "12312023");
        faturaNaoPaga = new Invoice("José", 1200.00, "12312023");
        listaDeBoletosFaturaPaga = new ArrayList<>(Arrays.asList(
                new PaymentSlip("91232341231014", "12152023", 500.00),
                new PaymentSlip("82342341231012", "12102023", 400.00),
                new PaymentSlip("76242341231011", "12052023", 600.00)
        ));
        listaDeBoletosFaturaNaoPaga = new ArrayList<>(Arrays.asList(
                new PaymentSlip("91232341231014", "12152023", 500.00),
                new PaymentSlip("82342341231012", "12102023", 400.00)
        ));
        processador = new PaymentProcessor();
    }

    @Test
    public void testProcessaFaturaPaga() {
        processador.processaBoletos(faturaPaga, listaDeBoletosFaturaPaga);

        assertEquals(faturaPaga.getStatus(), InvoiceStatus.PAGA);
        assertEquals(faturaPaga.getPagamentos().size(), 3);
    }

    @Test
    public void testProcessaFaturaNaoPaga() {
        processador.processaBoletos(faturaNaoPaga, listaDeBoletosFaturaNaoPaga);

        assertEquals(faturaNaoPaga.getStatus(), InvoiceStatus.NAO_PAGA);
        assertEquals(faturaNaoPaga.getPagamentos().size(), 2);
    }
}
