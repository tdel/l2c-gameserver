package main;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import configuration.ConfigGuiceModule;
import controller.ControllerGuiceModule;
import database.DatabaseGuiceModule;
import kernel.Kernel;
import kernel.KernelGuiceModule;
import model.repository.RepositoryGuiceModule;
import model.service.ServiceGuiceModule;
import network.NetworkGuiceModule;


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
