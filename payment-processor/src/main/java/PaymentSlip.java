public class PaymentSlip {
    private String codigo;
    private String data;
    private double valor;

    public PaymentSlip(String codigo, String data, double valor) {
        this.codigo = codigo;
        this.data = data;
        this.valor = valor;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getData() {
        return data;
    }

    public double getValor() {
        return valor;
    }
}
