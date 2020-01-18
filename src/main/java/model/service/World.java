package model.service;

import com.google.inject.Inject;
import model.instance.PlayerInstance;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.gameclient.ingame.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class World {

    private static final Logger logger = LogManager.getLogger("world");

    private Map<Integer, PlayerInstance> players;

    @Inject
    public World() {
        this.players = new ConcurrentHashMap<>();
    }

    public void addPlayer(PlayerInstance _player) {
        this.players.put(_player.getId(), _player);

        _player.sendPacket(new SkillsList(_player.getCharacter().getSkills()));
        _player.sendPacket(new PlayerStatsInfo(_player));
        CharacterInfo packet = new CharacterInfo(_player);

        for (PlayerInstance playerInRange: this.getPlayersInRange(_player, 3600).values()) {
            playerInRange.sendPacket(packet);
            _player.sendPacket(new CharacterInfo(playerInRange));
        }

        // Prevent relogin in game gfx.
        _player.sendPacket(new ValidateLocation(_player));

        // Unstuck players that had client open when server crashed.
        _player.sendPacket(new ActionFail());
    }

    public Map<Integer, PlayerInstance> getPlayersInRange(PlayerInstance _owner, int _radius) {

        Map<Integer, PlayerInstance> players = new HashMap<>();

        logger.info("Players count : " + this.players.size());

        for (PlayerInstance player : this.players.values()) {
            logger.info("Player : " + player.getName());
            if (player == _owner) {
                continue;
            }

            if (player.isInRange(_owner.getCoordinate(), _radius)) {
                players.put(player.getId(), player);
                logger.info("Adding player to range list : " + player.getName());
            }
        }

        return players;
    }


    public PlayerInstance getPlayer(int _id) {
        return this.players.get(_id);
    }




}
