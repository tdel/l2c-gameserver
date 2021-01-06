package kernel.database.dic;

import com.google.inject.Singleton;
import com.google.inject.persist.jpa.JpaPersistModule;
import kernel.database.kernel.DatabaseKernelModule;
import main.AbstractApplicationModule;

public class DatabaseGuiceModule extends AbstractApplicationModule {

    @Override
    protected void configure() {
        this.install(new JpaPersistModule("default"));

        this.bind(DatabaseKernelModule.class).in(Singleton.class);
        this.bindToKernel(DatabaseKernelModule.class);
    }
}
