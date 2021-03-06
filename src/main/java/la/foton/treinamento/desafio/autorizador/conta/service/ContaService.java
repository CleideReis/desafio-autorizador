package la.foton.treinamento.desafio.autorizador.conta.service;

import la.foton.treinamento.desafio.autorizador.cliente.entity.Cliente;
import la.foton.treinamento.desafio.autorizador.cliente.service.ClienteService;
import la.foton.treinamento.desafio.autorizador.common.exception.Mensagem;
import la.foton.treinamento.desafio.autorizador.common.exception.NegocioException;
import la.foton.treinamento.desafio.autorizador.conta.dao.ContaDAO;
import la.foton.treinamento.desafio.autorizador.conta.dao.LancamentoDAO;
import la.foton.treinamento.desafio.autorizador.conta.entity.Conta;
import la.foton.treinamento.desafio.autorizador.conta.entity.LancamentoDaConta;
import la.foton.treinamento.desafio.autorizador.conta.entity.TipoDoLancamento;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.Optional;

@Stateless
public class ContaService {

    @Inject
    private ContaDAO contaDAO;

    @Inject
    private LancamentoDAO lancamentoDAO;

    @EJB
    private ClienteService clienteService;

    public Conta cadastraConta(Conta conta, String cpf) throws NegocioException {
        Cliente cliente = clienteService.buscaPorCPF(cpf);
        conta.setTitular(cliente);
        contaDAO.insere(conta);
        return conta;
    }

    public void remove(Integer agencia, Integer numero) throws NegocioException {
        Conta conta = contaDAO.buscaConta(agencia, numero);
        if (new BigDecimal("0.0") != conta.getSaldo()) {
            throw new NegocioException(Mensagem.CONTA_NAO_PODE_SER_EXCLUIDA);
        }
        contaDAO.delete(conta);
    }

    public Conta consultaConta(Integer agencia, Integer numero) throws NegocioException {
        Conta conta = contaDAO.buscaConta(agencia, numero);
        if(conta == null) {
            throw new NegocioException(Mensagem.CONTA_NAO_ENCONTRADA, Response.Status.NOT_FOUND);
        }
        return conta;
    }




    public void atualizaConta(Conta conta) {
        contaDAO.atualiza(conta);
    }

    public void adicionaLancamento(Conta conta, BigDecimal valor, TipoDoLancamento tipoDoLancamento, String descricao) {
        LancamentoDaConta lancamentoDaConta = new LancamentoDaConta(tipoDoLancamento, descricao, valor);

        conta.getLancamentos().add(lancamentoDaConta);
    }

}
