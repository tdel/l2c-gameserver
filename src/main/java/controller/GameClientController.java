package controller;

import com.google.inject.Inject;
import com.google.inject.Provider;
import controller.gameclient.authed.RequestCharacterDeletion;
import controller.gameclient.authed.RequestCharacterSelection;
import controller.gameclient.connected.AuthLogin;
import controller.gameclient.connected.CheckProtocolVersion;
import controller.gameclient.authed.RequestCharacterCreation;
import controller.gameclient.authed.RequestNewCharacter;
import controller.gameclient.loadinggame.EnterWorld;
import network.gameclient.GameClientChannelHandler;
import network.gameclient.GameClientConnectionState;
import network.gameclient.packets.IncomingGameClientPacketInterface;
import network.gameclient.packets.PacketReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class GameClientController {

    private static final Logger logger = LogManager.getLogger();

    private final Map<GameClientConnectionState, Map<Integer, Provider<IncomingGameClientPacketInterface>>> controllers;
    private final ExGameClientController exController;


    @Inject
    public GameClientController(Map<Class, Provider<IncomingGameClientPacketInterface>> _controllers, ExGameClientController _exController) {

        this.exController = _exController;
        this.controllers = new HashMap<>();
        this.controllers.put(GameClientConnectionState.CONNECTED, new HashMap<>() {
            {
                put(0x0e, _controllers.get(CheckProtocolVersion.class));
                put(0x2B, _controllers.get(AuthLogin.class));
            }
        });
        this.controllers.put(GameClientConnectionState.AUTHED, new HashMap<>() {
            {
                put(0x13, _controllers.get(RequestNewCharacter.class));
                put(0x0c, _controllers.get(RequestCharacterCreation.class));
                put(0x0d, _controllers.get(RequestCharacterDeletion.class));
                put(0x12, _controllers.get(RequestCharacterSelection.class));
            }
        });
        this.controllers.put(GameClientConnectionState.LOADING_GAME, new HashMap<>() {
            {
                put(0x11, _controllers.get(EnterWorld.class));
            }
        });

    }

    public IncomingGameClientPacketInterface handle(PacketReader _reader, GameClientChannelHandler _client) {
        final int packetId = _reader.readC() & 0xFF;

        Map<Integer, Provider<IncomingGameClientPacketInterface>> map = this.controllers.get(_client.getState());
        if (null == map) {
            logger.error("State not found" + _client.getState());
            return null;
        }

        IncomingGameClientPacketInterface packet;

        Provider<IncomingGameClientPacketInterface> provider = map.get(packetId);
        if (null == provider) {
            // maybe EX ?
            if (packetId != 0xd0) {
                logger.error("Provider not found : 0x" + String.format("%02X", packetId) + " - state " + _client.getState());
                return null;
            }

            packet = this.exController.handle(_reader, _client);
        } else {
            packet = provider.get();
        }

        if (null == packet) {
            logger.error("Provider not loaded : 0x" + String.format("%02X", packetId) + " - state " + _client.getState());
            logger.error("Buffer : " + _reader.toString());

            return null;
        }


        logger.info("Packet found 0x" + String.format("%02X", packetId) + " <" + packet.getClass().getName() + ">");

        return packet;
    }
}
