package application.model.repository.template;

import com.google.inject.Inject;
import com.google.inject.Provider;
import application.model.entity.template.SexTemplate;
import application.model.repository.PreloadableRepositoryInterface;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class SexTemplateRepository implements PreloadableRepositoryInterface {

    private Provider<EntityManager> provider;

    @Inject
    public SexTemplateRepository(Provider<EntityManager> _provider) {
        this.provider = _provider;
    }

    @Override
    public void preload() {
        EntityManager em = this.provider.get();
        TypedQuery<SexTemplate> query = em.createQuery("SELECT r FROM SexTemplate r", SexTemplate.class);

        query.getResultList();
    }

    public SexTemplate findOneBy(byte _sex) {
        EntityManager em = this.provider.get();

        return em.find(SexTemplate.class, _sex);
    }

}
