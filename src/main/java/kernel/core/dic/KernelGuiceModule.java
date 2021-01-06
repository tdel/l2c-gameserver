package kernel.core.dic;

import com.google.inject.Singleton;
import kernel.core.Kernel;
import main.AbstractApplicationModule;

public class KernelGuiceModule extends AbstractApplicationModule {

    @Override
    protected void configure() {
        this.bind(Kernel.class).in(Singleton.class);
    }

}
