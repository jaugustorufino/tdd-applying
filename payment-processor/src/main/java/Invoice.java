import java.util.ArrayList;
import java.util.List;

public class Invoice {
    private String nomeCliente;
    private double valor;
    private String data;
    private InvoiceStatus status;
    private List<Payment> pagamentos;

    public Invoice(String nome, double valor, String data) {
        this.nomeCliente = nome;
        this.valor = valor;
        this.data = data;
        this.status = InvoiceStatus.NAO_PAGA;
        this.pagamentos = new ArrayList<>();
    }

    public String getNomeCliente() {
        return this.nomeCliente;
    }

    public double getValor() {
        return this.valor;
    }

    public String getData() {
        return this.data;
    }

    public InvoiceStatus getStatus() {
        return this.status;
    }

    public void setStatus(InvoiceStatus status) {
        this.status = status;
    }

    public List<Payment> getPagamentos() {
        return this.pagamentos;
    }
}
