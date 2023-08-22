import java.util.ArrayList;
import java.util.List;

public class Fatura {
    private String nomeCliente;
    private final double valor;
    private double valorAtual;
    private String data;
    private StatusFatura status;
    private List<Pagamento> pagamentos;

    public Fatura(String nome, double valor, String data) {
        this.nomeCliente = nome;
        this.valor = valor;
        this.valorAtual = valor;
        this.data = data;
        this.status = StatusFatura.NAO_PAGA;
        this.pagamentos = new ArrayList<>();
    }

    public String getNomeCliente() {
        return this.nomeCliente;
    }

    public double getValor() {
        return this.valor;
    }

    public double getValorAtual() {
        return this.valorAtual;
    }

    public String getData() {
        return this.data;
    }

    public StatusFatura getStatus() {
        return this.status;
    }

    public void setStatus(StatusFatura status) {
        this.status = status;
    }

    public List<Pagamento> getPagamentos() {
        return this.pagamentos;
    }

    public void addPagamento(Pagamento pagamento) {
        this.pagamentos.add(pagamento);
        this.valorAtual = this.valorAtual - pagamento.getValorPago();
    }
}
