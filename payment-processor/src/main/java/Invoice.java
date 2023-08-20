public class Invoice {
    private String nomeCliente;
    private double valor;
    private String data;
    private InvoiceStatus status;

    public Invoice(String nome, double valor, String data) {
        this.nomeCliente = nome;
        this.valor = valor;
        this.data = data;
        this.status = InvoiceStatus.NAO_PAGA;
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
}
