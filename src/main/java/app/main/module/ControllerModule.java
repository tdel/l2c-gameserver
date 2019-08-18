package app.main.module;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import controller.gameclient.connected.CheckProtocolVersion;
import controller.gameclient.connected.RequestGoToCharacterSelection;
import controller.gameclient.connected.RequestNewCharacter;


public class ControllerModule extends AbstractModule {

    @Override
    protected void configure() {
        this.bind(CheckProtocolVersion.class).in(Singleton.class);
        this.bind(RequestNewCharacter.class).in(Singleton.class);
        this.bind(RequestNewCharacter.class).in(Singleton.class);
        this.bind(RequestGoToCharacterSelection.class).in(Singleton.class);
    }
}
