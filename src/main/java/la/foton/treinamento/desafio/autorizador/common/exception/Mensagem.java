package la.foton.treinamento.desafio.autorizador.common.exception;

public enum Mensagem {

    /* Mensagens de Negócio */
    ERRO_GENERICO("Contate o administrador da aplicação"), //
    LOG_INVALIDO("Log inválido"), //
    CLIENTE_NAO_ENCONTRADO("Cliente não encontrado."),
    CONTA_NAO_ENCONTRADA("Conta não encontrada."),
    VALOR_PRECISA_SER_MAIOR_QUE_ZERO("O valor precisa ser maior que R$ 00,00"),
    SALDO_INSUFICIENTE("Saldo Insuficiente."),
    CLIENTE_NAO_PODE_SER_CADASTRADO("Cliente não pode ser cadastrado"),

    // Mensagens de Infraestrutura
    AUTORIZADOR_NAO_ENCONTRADO("Nao existe um autorizador para o código de transação {0}"), ERRO_CONVERSAO_JSON("Erro ao converter para JSON");

    private String texto;

    private Mensagem(String texto) {
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }

}
