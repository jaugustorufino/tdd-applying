import java.util.Date;

public class Pagamento {
    private double valorPago;
    private Date data;
    private TipoPagamento tipo;

    public Pagamento(double valorPago, TipoPagamento tipo) {
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

    public TipoPagamento getTipo() {
        return tipo;
    }
}