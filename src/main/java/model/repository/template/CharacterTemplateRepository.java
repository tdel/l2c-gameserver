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

    private List<CharacterTemplate> charsTemplate;

    @Inject
    public CharacterTemplateRepository(Provider<EntityManager> _provider) {
        this.provider = _provider;
    }

    @Override
    public void preload() {
        EntityManager em = this.provider.get();
        TypedQuery<CharacterTemplate> query = em.createQuery("SELECT ct FROM CharacterTemplate ct", CharacterTemplate.class);

        this.charsTemplate = query.getResultList();
    }

    public List<CharacterTemplate> findAll() {
        return this.charsTemplate;
    }

    public CharacterTemplate findOneById(int _id) {
        EntityManager em = this.provider.get();

        return em.find(CharacterTemplate.class, _id);
        /*
        for (CharacterTemplate charTemplate : this.charsTemplate) {
            if (charTemplate.getId() == _id) {
                return charTemplate;
            }
        }

        return null;*/
    }
}
