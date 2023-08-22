public enum StatusFatura {
    NAO_PAGA("A fatura não foi paga."),
    PAGA("A fatura foi paga com sucesso.");

    private final String descricao;

    StatusFatura(String descricao) {
        this.descricao = descricao;
    }
}
