package model.repository.template;

import com.google.inject.Inject;
import com.google.inject.Provider;
import model.entity.template.CharacterTemplate;
import model.repository.PreloadableRepositoryInterface;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class CharacterTemplateRepository implements PreloadableRepositoryInterface {

    private Provider<EntityManager> provider;

    @Inject
    public CharacterTemplateRepository(Provider<EntityManager> _provider) {
        this.provider = _provider;
    }

    @Override
    public void preload() {
        this.findAll();
    }

    public List<CharacterTemplate> findAll() {
        EntityManager em = this.provider.get();
        TypedQuery<CharacterTemplate> query = em.createQuery("SELECT ct FROM CharacterTemplate ct", CharacterTemplate.class);

        return query.getResultList();
    }

    public List<CharacterTemplate> findAllBaseClasses() {
        EntityManager em = this.provider.get();
        TypedQuery<CharacterTemplate> query = em.createQuery("SELECT ct FROM CharacterTemplate ct WHERE ct.parent IS NULL", CharacterTemplate.class);

        return query.getResultList();
    }

    public CharacterTemplate findOneById(int _classId) {
        EntityManager em = this.provider.get();
        TypedQuery<CharacterTemplate> query = em.createQuery("SELECT ct FROM CharacterTemplate ct WHERE ct.id = :classId", CharacterTemplate.class);
        query.setParameter("classId", _classId);

        return query.getSingleResult();
    }
}
