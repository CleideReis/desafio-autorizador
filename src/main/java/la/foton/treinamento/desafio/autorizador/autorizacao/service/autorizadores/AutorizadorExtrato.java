package la.foton.treinamento.desafio.autorizador.autorizacao.service.autorizadores;

import la.foton.treinamento.desafio.autorizador.autorizacao.entity.Autorizacao;
import la.foton.treinamento.desafio.autorizador.autorizacao.service.AbstractAutorizador;
import la.foton.treinamento.desafio.autorizador.autorizacao.service.Autorizador;
import la.foton.treinamento.desafio.autorizador.common.configuration.JSONConverter;
import la.foton.treinamento.desafio.autorizador.common.exception.InfraestruturaException;
import la.foton.treinamento.desafio.autorizador.common.exception.NegocioException;
import la.foton.treinamento.desafio.autorizador.conta.entity.Conta;
import la.foton.treinamento.desafio.autorizador.conta.service.ContaService;
import la.foton.treinamento.desafio.autorizador.transacao.entity.TipoDaTransacao;
import la.foton.treinamento.desafio.autorizador.transacao.entity.Transacao;

import javax.ejb.EJB;

@Autorizador(transacao = TipoDaTransacao.EXTRATO)
public class AutorizadorExtrato extends AbstractAutorizador {

    @EJB
    private ContaService contaService;

    @Override
    protected void executaRegrasEspecificas(Transacao transacao, Autorizacao autorizacao) throws NegocioException, InfraestruturaException {
        Conta conta = contaService.consultaConta(transacao.getAgencia(), transacao.getConta());
        autorizacao.setParticao(JSONConverter.toJSONFromObject(conta.getLancamentos()));
    }
}
