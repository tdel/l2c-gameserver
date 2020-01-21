package main;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import kernel.configuration.ConfigGuiceModule;
import application.core.controller.ControllerGuiceModule;
import kernel.database.DatabaseGuiceModule;
import kernel.core.Kernel;
import kernel.core.KernelGuiceModule;
import application.model.repository.RepositoryGuiceModule;
import application.model.service.ServiceGuiceModule;
import kernel.network.NetworkGuiceModule;


public class Main {

    public static void main(String[] args) throws Exception {

        Injector injector = Guice.createInjector(Stage.DEVELOPMENT,
                new KernelGuiceModule(),
                new ConfigGuiceModule("src/main/resources/app.properties"),
                new DatabaseGuiceModule(),
                new RepositoryGuiceModule(),
                new ServiceGuiceModule(),
                new ControllerGuiceModule(),
                new NetworkGuiceModule()
        );

        Kernel kernel = injector.getInstance(Kernel.class);
        kernel.start();
    }
}
