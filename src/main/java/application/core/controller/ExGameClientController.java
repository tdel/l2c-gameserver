package application.core.controller;

import com.google.inject.Inject;
import com.google.inject.Provider;
import application.lobby.controller.RequestGoToCharacterSelection;
import application.controller.gameclient.loadinggame.ex.RequestKeyMapping;
import application.controller.gameclient.loadinggame.ex.RequestManorList;
import kernel.network.gameclient.GameClientChannelHandler;
import kernel.network.gameclient.GameClientConnectionState;
import kernel.network.gameclient.packets.PacketReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class ExGameClientController {

    private static final Logger logger = LogManager.getLogger("kernel/network");

    private final Map<GameClientConnectionState, Map<Integer, Provider<ExPacketInterface>>> controllers;

    @Inject
    public ExGameClientController(Map<Class, Provider<ExPacketInterface>> _controllers) {
        this.controllers = new HashMap<>();
        this.controllers.put(GameClientConnectionState.AUTHED, new HashMap<>() {
            {
                put(0x36, _controllers.get(RequestGoToCharacterSelection.class));
            }

        });
        this.controllers.put(GameClientConnectionState.LOADING_GAME, new HashMap<>() {
            {
                put(0x01, _controllers.get(RequestManorList.class));
                put(0x21, _controllers.get(RequestKeyMapping.class));
            }
        });
        this.controllers.put(GameClientConnectionState.INGAME, new HashMap<>() {
            {
                put(0x01, _controllers.get(RequestManorList.class));
                put(0x21, _controllers.get(RequestKeyMapping.class));
            }
        });

    }

    public ExPacketInterface handle(PacketReader _reader, GameClientChannelHandler _client) {
        if (_reader.getReadableBytes() < 2) {
            logger.error("2nd OPCode not present");

            return null;
        }

        int packetId = _reader.readShort() & 0xFF;

        Map<Integer, Provider<ExPacketInterface>> map = this.controllers.get(_client.getState());
        if (null == map) {
            logger.error("State not found" + _client.getState());
            return null;
        }

        Provider<ExPacketInterface> provider = map.get(packetId);
        if (null == provider) {
            logger.error("Provider not found : 0x" + String.format("%02X", packetId) + " - state " + _client.getState());
            return null;
        }

        ExPacketInterface packet = provider.get();
        if (null == packet) {
            logger.error("Provider not loaded : 0x" + String.format("%02X", packetId) + " - state " + _client.getState());
            return null;
        }

        logger.info("Packet found 0x" + String.format("%02X", packetId) + " <" + packet.getClass().getName() + ">");

        return packet;
    }
}
