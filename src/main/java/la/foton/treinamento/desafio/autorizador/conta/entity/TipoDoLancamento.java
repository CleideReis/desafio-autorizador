package la.foton.treinamento.desafio.autorizador.conta.entity;

public enum TipoDoLancamento {

	DEBITO(1, "Débito"), CREDITO(2, "Crédito"), NAO_APLICAVEL(0,"Não aplicável" );

	private Integer chave;
	private String valor;

	private TipoDoLancamento(Integer chave, String valor) {
		this.chave = chave;
		this.valor = valor;
	}

	public Integer getChave() {
		return chave;
	}

	public String getValor() {
		return valor;
	}
}
