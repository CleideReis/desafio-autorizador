package la.foton.treinamento.desafio.autorizador.autorizacao.service.autorizadores;

import la.foton.treinamento.desafio.autorizador.autorizacao.entity.Autorizacao;
import la.foton.treinamento.desafio.autorizador.autorizacao.service.AbstractAutorizador;
import la.foton.treinamento.desafio.autorizador.autorizacao.service.Autorizador;
import la.foton.treinamento.desafio.autorizador.common.exception.NegocioException;
import la.foton.treinamento.desafio.autorizador.conta.entity.Conta;
import la.foton.treinamento.desafio.autorizador.conta.entity.TipoDoLancamento;
import la.foton.treinamento.desafio.autorizador.conta.service.ContaService;
import la.foton.treinamento.desafio.autorizador.transacao.entity.TipoDaTransacao;
import la.foton.treinamento.desafio.autorizador.transacao.entity.Transacao;
import la.foton.treinamento.desafio.autorizador.transacao.entity.TransacaoFinaceira;

import javax.ejb.EJB;

@Autorizador(transacao = TipoDaTransacao.SAQUE)
public class AutorizadorSaque extends AbstractAutorizador {

    @EJB
    private ContaService contaService;

    @Override
    protected void executaRegrasEspecificas(Transacao transacao, Autorizacao autorizacao) throws NegocioException {
        TransacaoFinaceira transacaoFinaceira = (TransacaoFinaceira) transacao;
        Conta conta = contaService.consultaConta(transacao.getAgencia(), transacao.getConta());
        conta.debita(transacaoFinaceira.getValor());
        contaService.geraLancamento(conta, transacaoFinaceira.getValor(), TipoDoLancamento.DEBITO, "Débito em conta");
        contaService.atualizaConta(conta);
    }



}
