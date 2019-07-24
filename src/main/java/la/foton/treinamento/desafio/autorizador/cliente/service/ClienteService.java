package la.foton.treinamento.desafio.autorizador.cliente.service;

import la.foton.treinamento.desafio.autorizador.cliente.dao.ClienteDAO;
import la.foton.treinamento.desafio.autorizador.cliente.entity.Cliente;
import la.foton.treinamento.desafio.autorizador.common.exception.Mensagem;
import la.foton.treinamento.desafio.autorizador.common.exception.NegocioException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

@Stateless
public class ClienteService {

    @Inject
    private ClienteDAO dao;

    public Cliente cadastraCliente(Cliente cliente) throws NegocioException {
        if (cliente.getCpf() == null || cliente.getCpf().isEmpty() || cliente.getNome() == null || cliente.getNome().isEmpty()) {
            throw new NegocioException(Mensagem.CLIENTE_NAO_PODE_SER_CADASTRADO);
        }
        dao.insere(cliente);
        return cliente;
    }

    public void ativaCliente(String cpf) throws NegocioException {
        Cliente cliente = buscaPorCPF(cpf);
        cliente.ativa();
        dao.atualiza(cliente);
    }

    public Cliente buscaPorCPF(String cpf) throws NegocioException {
        Cliente cliente = dao.buscaPorCpf(cpf);
        if (cliente == null) {
            throw new NegocioException(Mensagem.CLIENTE_NAO_ENCONTRADO);
        }
        return cliente;
    }

    public void remove(String cpf) throws NegocioException {
        Cliente cliente = buscaPorCPF(cpf);
        dao.delete(cliente);
    }


}
