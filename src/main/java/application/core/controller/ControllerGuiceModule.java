package application.core.controller;

import application.lobby.controller.*;
import com.google.inject.Singleton;
import application.controller.gameclient.ingame.*;
import application.controller.gameclient.loadinggame.EnterWorld;
import application.controller.gameclient.loadinggame.ex.RequestKeyMapping;
import application.controller.gameclient.loadinggame.ex.RequestManorList;
import main.AbstractApplicationModule;
import kernel.network.gameclient.packets.IncomingGameClientPacketInterface;


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
        this.bindToPacketInterface(RequestShowMinimap.class);
        this.bindToPacketInterface(RequestTargetCancel.class);
        this.bindToPacketInterface(RequestLogout.class);
        this.bindToPacketInterface(RequestRestart.class);
        this.bindToPacketInterface(RequestSkillList.class);
        this.bindToPacketInterface(RequestMagicSkillUse.class);

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
