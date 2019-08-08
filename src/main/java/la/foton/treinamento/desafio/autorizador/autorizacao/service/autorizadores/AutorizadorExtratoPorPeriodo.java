package la.foton.treinamento.desafio.autorizador.autorizacao.service.autorizadores;

import la.foton.treinamento.desafio.autorizador.autorizacao.entity.Autorizacao;
import la.foton.treinamento.desafio.autorizador.autorizacao.service.AbstractAutorizador;
import la.foton.treinamento.desafio.autorizador.autorizacao.service.Autorizador;
import la.foton.treinamento.desafio.autorizador.common.configuration.JSONConverter;
import la.foton.treinamento.desafio.autorizador.common.exception.InfraestruturaException;
import la.foton.treinamento.desafio.autorizador.common.exception.NegocioException;
import la.foton.treinamento.desafio.autorizador.conta.entity.Conta;
import la.foton.treinamento.desafio.autorizador.conta.entity.LancamentoDaConta;
import la.foton.treinamento.desafio.autorizador.conta.service.ContaService;
import la.foton.treinamento.desafio.autorizador.transacao.entity.TipoDaTransacao;
import la.foton.treinamento.desafio.autorizador.transacao.entity.Transacao;
import la.foton.treinamento.desafio.autorizador.transacao.entity.TransacaoExtratoPorPeriodo;

import javax.ejb.EJB;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Autorizador(transacao = TipoDaTransacao.EXTRATO_POR_PERIODO)
public class AutorizadorExtratoPorPeriodo extends AbstractAutorizador {

    @EJB
    private ContaService contaService;

    @Override
    protected void executaRegrasEspecificas(Transacao transacao, Autorizacao autorizacao) throws NegocioException, InfraestruturaException {
        TransacaoExtratoPorPeriodo transacaoExtratoPorPeriodo = (TransacaoExtratoPorPeriodo) transacao;
        Conta conta = contaService.consultaConta(transacaoExtratoPorPeriodo.getAgencia(), transacaoExtratoPorPeriodo.getConta());
        List<LancamentoDaConta> extrato = new ArrayList<>();
        for(LancamentoDaConta lancamentoDaConta: conta.getLancamentos()){
            LocalDate dataInicial = transacaoExtratoPorPeriodo.getDataInicial();
            LocalDate dataFinal = transacaoExtratoPorPeriodo.getDataFinal();
            LocalDate dataTransacao = lancamentoDaConta.getDataHora().toLocalDate();
            if((dataTransacao.isAfter(dataInicial) || dataTransacao.isEqual(dataInicial)) && (dataTransacao.isBefore(dataFinal) || dataTransacao.isEqual(dataInicial))){
                extrato.add(lancamentoDaConta);
            }
        }
        autorizacao.setParticao(JSONConverter.toJSONFromObject(extrato));
    }
}
