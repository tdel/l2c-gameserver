package model.repository.template;

import com.google.inject.Inject;
import com.google.inject.Provider;
import model.entity.template.RaceTemplate;
import model.repository.PreloadableRepositoryInterface;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class RaceTemplateRepository implements PreloadableRepositoryInterface {

    private Provider<EntityManager> provider;

    private List<RaceTemplate> racesTemplate;

    @Inject
    public RaceTemplateRepository(Provider<EntityManager> _provider) {
        this.provider = _provider;
    }

    @Override
    public void preload() {
        EntityManager em = this.provider.get();
        TypedQuery<RaceTemplate> query = em.createQuery("SELECT r FROM RaceTemplate r", RaceTemplate.class);

        this.racesTemplate = query.getResultList();
    }


}
