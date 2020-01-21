package application.model.repository.template;

import com.google.inject.Inject;
import com.google.inject.Provider;
import application.model.entity.template.RaceTemplate;
import application.model.repository.PreloadableRepositoryInterface;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class RaceTemplateRepository implements PreloadableRepositoryInterface {

    private Provider<EntityManager> provider;

    @Inject
    public RaceTemplateRepository(Provider<EntityManager> _provider) {
        this.provider = _provider;
    }

    @Override
    public void preload() {
        EntityManager em = this.provider.get();
        TypedQuery<RaceTemplate> query = em.createQuery("SELECT r FROM RaceTemplate r", RaceTemplate.class);

        query.getResultList();
    }


}
