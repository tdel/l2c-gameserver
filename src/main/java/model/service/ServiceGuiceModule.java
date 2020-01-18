package model.service;

import com.google.inject.Singleton;
import kernel.Kernel;
import main.AbstractApplicationModule;

public class ServiceGuiceModule extends AbstractApplicationModule {

    @Override
    protected void configure() {
        this.bind(World.class).in(Singleton.class);
    }

}
