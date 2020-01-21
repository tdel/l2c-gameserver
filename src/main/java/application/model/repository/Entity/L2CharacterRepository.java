package application.model.repository.Entity;

import com.google.inject.Inject;
import com.google.inject.Provider;
import application.model.entity.L2Character;

import javax.persistence.*;
import java.util.List;

public class L2CharacterRepository {

    private Provider<EntityManager> provider;

    @Inject
    public L2CharacterRepository(Provider<EntityManager> _provider) {
        this.provider = _provider;
    }


    public List<L2Character> getAllByAccountId(int _accountId) {
        EntityManager em = this.provider.get();
        TypedQuery<L2Character> query = em.createQuery("SELECT c FROM L2Character c WHERE c.accountId = :accountId", L2Character.class);
        query.setParameter("accountId", _accountId);

        return query.getResultList();
    }

    public L2Character findOneBySlot(int _accountId, int _charSlot) {
        List<L2Character> characters = this.getAllByAccountId(_accountId);

        return characters.get(_charSlot);
    }

    public void save(L2Character _character) {
        EntityManager em = this.provider.get();
        em.persist(_character);

        this.flush(em);
    }

    public void remove(L2Character _character) {
        EntityManager em = this.provider.get();
        em.remove(_character);

        this.flush(em);
    }

    private void flush(EntityManager _em) {
        _em.getTransaction().begin();
        _em.flush();
        _em.getTransaction().commit();
    }
}
