public enum InvoiceStatus {
    NAO_PAGA("A fatura não foi paga."),
    PAGA("A fatura foi paga com sucesso.");

    private final String descricao;

    InvoiceStatus(String descricao) {
        this.descricao = descricao;
    }
}
