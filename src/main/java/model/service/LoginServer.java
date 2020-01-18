package model.service;

import network.gameclient.GameClientChannelHandler;
import network.loginserver.LoginServerChannelHandler;

import java.util.List;

public class LoginServer {

    private LoginServerChannelHandler client;

    private List<GameClientChannelHandler> waitingClients;

    public void sendRequestAccount(GameClientChannelHandler _client, int _key1, int _key2) {
        this.waitingClients.add(_client);
        this.client.sendPacket("{code:'account-info', key1:" + _key1 + ", key2:" + _key2 + "}");
    }


}
