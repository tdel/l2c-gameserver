package controller;

import com.google.inject.Singleton;
import controller.gameclient.authed.*;
import controller.gameclient.connected.*;
import controller.gameclient.ingame.RequestMoveToLocation;
import controller.gameclient.ingame.RequestValidatePosition;
import controller.gameclient.loadinggame.EnterWorld;
import controller.gameclient.loadinggame.ex.RequestKeyMapping;
import controller.gameclient.loadinggame.ex.RequestManorList;
import main.AbstractApplicationModule;
import net.bytebuddy.asm.Advice;
import network.gameclient.packets.IncomingGameClientPacketInterface;


public class ControllerGuiceModule extends AbstractApplicationModule {

    @Override
    protected void configure() {
        this.bindToPacketInterface(CheckProtocolVersion.class);
        this.bindToPacketInterface(RequestNewCharacter.class);
        this.bindToPacketInterface(RequestCharacterCreation.class);
        this.bindToPacketInterface(RequestCharacterDeletion.class);
        this.bindToPacketInterface(AuthLogin.class);
        this.bindToPacketInterface(RequestCharacterSelection.class);
        this.bindToPacketInterface(EnterWorld.class);
        this.bindToPacketInterface(RequestMoveToLocation.class);
        this.bindToPacketInterface(RequestValidatePosition.class);

        this.bindToPacketInterfaceEX(RequestGoToCharacterSelection.class);
        this.bindToPacketInterfaceEX(RequestManorList.class);
        this.bindToPacketInterfaceEX(RequestKeyMapping.class);

        this.bind(GameClientController.class).in(Singleton.class);
        this.bind(ExGameClientController.class).in(Singleton.class);
    }

    private void bindToPacketInterface(Class<? extends IncomingGameClientPacketInterface> _class) {
        this.bind(_class).in(Singleton.class);
        this.bindToMap(IncomingGameClientPacketInterface.class, _class);
    }

    private void bindToPacketInterfaceEX(Class<? extends ExPacketInterface> _class) {
        this.bind(_class).in(Singleton.class);
        this.bindToMap(ExPacketInterface.class, _class);
    }

}