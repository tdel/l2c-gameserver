package model.repository;

import com.google.inject.Singleton;
import main.AbstractApplicationModule;
import model.repository.template.CharacterTemplateRepository;

public class RepositoryGuiceModule extends AbstractApplicationModule {

    @Override
    protected void configure() {
        this.bind(CharacterTemplateRepository.class).in(Singleton.class);

        this.bind(RepositoryKernelModule.class).in(Singleton.class);
        this.bindToKernel(RepositoryKernelModule.class);


        this.bindToMap(PreloadableRepositoryInterface.class, CharacterTemplateRepository.class);
    }
}
