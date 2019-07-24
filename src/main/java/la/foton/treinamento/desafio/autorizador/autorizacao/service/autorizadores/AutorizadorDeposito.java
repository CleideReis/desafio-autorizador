package la.foton.treinamento.desafio.autorizador.autorizacao.service.autorizadores;

import la.foton.treinamento.desafio.autorizador.autorizacao.entity.Autorizacao;
import la.foton.treinamento.desafio.autorizador.autorizacao.service.AbstractAutorizador;
import la.foton.treinamento.desafio.autorizador.autorizacao.service.Autorizador;
import la.foton.treinamento.desafio.autorizador.common.exception.NegocioException;
import la.foton.treinamento.desafio.autorizador.conta.entity.Conta;
import la.foton.treinamento.desafio.autorizador.conta.entity.TipoDoLancamento;
import la.foton.treinamento.desafio.autorizador.conta.service.ContaService;
import la.foton.treinamento.desafio.autorizador.log.entity.Log;
import la.foton.treinamento.desafio.autorizador.transacao.entity.TipoDaTransacao;
import la.foton.treinamento.desafio.autorizador.transacao.entity.Transacao;

import javax.ejb.EJB;

@Autorizador(transacao = TipoDaTransacao.DEPOSITO_EM_CONTA)
public class AutorizadorDeposito extends AbstractAutorizador {

    @EJB
    private ContaService contaService;

    @Override
    protected void executaRegrasEspecificas(Transacao transacao) throws NegocioException {
        Conta conta = contaService.consultaConta(transacao.getAgencia(), transacao.getConta());
        conta.credita(transacao.getValor());
        contaService.geraLancamento(conta, transacao.getValor(), TipoDoLancamento.CREDITO, "Crédito em conta");
        contaService.atualizaConta(conta);
    }



}
