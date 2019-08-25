package app.main.module;

import subsystem.network.NetworkSubsystem;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import app.kernel.Kernel;
import subsystem.AbstractKernelSubsystem;

import java.util.HashSet;
import java.util.Set;

public class KernelModule extends AbstractModule {

    @Override
    protected void configure() {
        this.bind(Kernel.class).in(Singleton.class);
        this.bind(NetworkSubsystem.class).in(Singleton.class);
    }

    public Set<Class<? extends AbstractKernelSubsystem>> getSubsystems() {
        Set<Class<? extends AbstractKernelSubsystem>> set = new HashSet<>();

        set.add(NetworkSubsystem.class);

        return set;
    }
}
