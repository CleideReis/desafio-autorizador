package la.foton.treinamento.desafio.autorizador.transacao.entity;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class TransacaoFinaceira extends Transacao {

    @NotNull
    private BigDecimal valor;

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
