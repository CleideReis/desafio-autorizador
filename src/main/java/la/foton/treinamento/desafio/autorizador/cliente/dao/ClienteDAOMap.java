package la.foton.treinamento.desafio.autorizador.cliente.dao;

import la.foton.treinamento.desafio.autorizador.cliente.entity.Cliente;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class ClienteDAOMap implements ClienteDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insere(Cliente cliente) {
        em.persist(cliente);
    }

    @Override
    public void atualiza(Cliente cliente) {
        em.merge(cliente);
    }

    @Override
    public Cliente buscaPorCpf(String cpf) {
        return em.find(Cliente.class, cpf);
    }


    @Override
    public void delete(Cliente cliente) {
        em.remove(cliente);
    }

}
