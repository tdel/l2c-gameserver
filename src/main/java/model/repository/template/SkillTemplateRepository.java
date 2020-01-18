package model.repository.template;

import com.google.inject.Inject;
import com.google.inject.Provider;
import model.entity.template.SkillLevelTemplate;
import model.entity.template.SkillTemplate;
import model.repository.PreloadableRepositoryInterface;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class SkillTemplateRepository implements PreloadableRepositoryInterface {

    private Provider<EntityManager> provider;

    @Inject
    public SkillTemplateRepository(Provider<EntityManager> _provider) {
        this.provider = _provider;
    }

    @Override
    public void preload() {
        EntityManager em = this.provider.get();

        TypedQuery<SkillTemplate> query = em.createQuery("SELECT r FROM SkillTemplate r", SkillTemplate.class);
        query.getResultList();

        TypedQuery<SkillLevelTemplate> query2 = em.createQuery("SELECT r FROM SkillLevelTemplate r", SkillLevelTemplate.class);
        query2.getResultList();
    }

}
