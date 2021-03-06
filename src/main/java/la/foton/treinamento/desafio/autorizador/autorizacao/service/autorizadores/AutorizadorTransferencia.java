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
import la.foton.treinamento.desafio.autorizador.transacao.entity.TransacaoTransferencia;

import javax.ejb.EJB;

@Autorizador(transacao = TipoDaTransacao.TRANSFERENCIA)
public class AutorizadorTransferencia extends AbstractAutorizador {

    @EJB
    private ContaService contaService;

    @Override
    protected void executaRegrasEspecificas(Transacao transacao, Autorizacao autorizacao) throws NegocioException {
        TransacaoTransferencia transacaoTransferencia = (TransacaoTransferencia) transacao;
        Conta conta = contaService.consultaConta(transacaoTransferencia.getAgencia(), transacaoTransferencia.getConta());
        Conta contaFavorecido = contaService.consultaConta(transacaoTransferencia.getAgenciaFavorecido(),
                transacaoTransferencia.getContaFavorecido());
        conta.transfere(transacaoTransferencia.getValor(), contaFavorecido);
        contaService.adicionaLancamento(conta, transacaoTransferencia.getValor(), TipoDoLancamento.DEBITO, "Transferêcia entre contas");
        contaService.adicionaLancamento(contaFavorecido, transacaoTransferencia.getValor(), TipoDoLancamento.CREDITO, "Transferêcia entre contas");
        contaService.atualizaConta(conta);
        contaService.atualizaConta(contaFavorecido);
    }



}
