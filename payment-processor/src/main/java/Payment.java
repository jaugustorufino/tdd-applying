import java.util.Date;

public class Payment {
    private double valorPago;
    private Date data;
    private PaymentType tipo;

    public Payment(double valorPago, PaymentType tipo) {
        this.valorPago = valorPago;
        this.data = new Date(System.currentTimeMillis());;
        this.tipo = tipo;
    }

    public double getValorPago() {
        return valorPago;
    }

    public Date getData() {
        return data;
    }

    public PaymentType getTipo() {
        return tipo;
    }
}