package la.foton.treinamento.desafio.autorizador.conta.dao;

import la.foton.treinamento.desafio.autorizador.cliente.entity.Cliente;
import la.foton.treinamento.desafio.autorizador.conta.entity.Conta;

import java.util.Optional;

public interface ContaDAO {

    void insere(Conta conta);

    void atualiza(Conta conta);

    void delete(Conta conta);

    Conta buscaConta(Integer agencia, Integer numero);
}
