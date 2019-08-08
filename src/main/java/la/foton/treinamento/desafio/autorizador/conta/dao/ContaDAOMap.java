package la.foton.treinamento.desafio.autorizador.conta.dao;

import la.foton.treinamento.desafio.autorizador.conta.entity.Conta;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Optional;

public class ContaDAOMap implements ContaDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insere(Conta conta) {
        conta.setNumero(geraId());
        em.persist(conta);
    }

    @Override
    public void atualiza(Conta conta) {
        em.merge(conta);
    }

    @Override
    public void delete(Conta conta) {
        em.remove(conta);
    }

    @Override
    public Conta buscaConta(Integer agencia, Integer numero) {
        TypedQuery<Conta> query = em.createQuery("SELECT c FROM Conta c WHERE c.agencia = :agencia AND c.numero = :numero", Conta.class);
        query.setParameter("agencia", agencia);
        query.setParameter("numero", numero);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    private Integer geraId() {
        TypedQuery<Integer> query = em.createQuery("SELECT max(c.numero) FROM Conta c", Integer.class);
        return query.getSingleResult() == null ? 1 : query.getSingleResult() + 1;
    }
}
