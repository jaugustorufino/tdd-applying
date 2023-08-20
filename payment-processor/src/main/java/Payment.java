public class Payment {
    private double valorPago;
    private String data;
    private PaymentType tipo;

    public Payment(double valorPago, String data, PaymentType tipo) {
        this.valorPago = valorPago;
        this.data = data;
        this.tipo = tipo;
    }
}