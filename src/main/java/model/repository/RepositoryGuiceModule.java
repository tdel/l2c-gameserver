package model.repository;

import com.google.inject.Singleton;
import main.AbstractApplicationModule;
import model.repository.template.CharacterTemplateRepository;
import model.repository.template.RaceTemplateRepository;

public class RepositoryGuiceModule extends AbstractApplicationModule {

    @Override
    protected void configure() {
        this.bind(RepositoryKernelModule.class).in(Singleton.class);
        this.bindToKernel(RepositoryKernelModule.class);


        this.bindToPreloadable(CharacterTemplateRepository.class);
        this.bindToPreloadable(RaceTemplateRepository.class);
    }

    private void bindToPreloadable(Class<? extends PreloadableRepositoryInterface> _class) {
        this.bind(_class).in(Singleton.class);

        this.bindToMap(PreloadableRepositoryInterface.class, _class);
    }
}
