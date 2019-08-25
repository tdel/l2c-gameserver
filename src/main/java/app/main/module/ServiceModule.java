package app.main.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.AbstractModule;


public class ServiceModule extends AbstractModule {

    @Override
    protected void configure() {
        Gson gson = new GsonBuilder().serializeNulls().create();
        this.bind(Gson.class).toInstance(gson);
    }

}

