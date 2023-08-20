public class Invoice {
    private String nomeCliente;
    private double valor;
    private String data;

    public Invoice(String nome, double valor, String data) {
        this.nomeCliente = nome;
        this.valor = valor;
        this.data = data;
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
}
