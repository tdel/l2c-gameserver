package model.entity.instance;

import model.entity.L2Character;
import network.gameclient.GameClientChannelHandler;
import network.gameclient.packets.OutgoingGameClientPacketInterface;

import java.util.HashMap;
import java.util.Map;

public class PlayerInstance {

    private int id;
    private GameClientChannelHandler network;
    private L2Character character;

    private String title;

    private int hp;
    private int mp;
    private int inventoryLoad;

    private int x;
    private int y;
    private int z;
    private int heading;

    private int destinationX;
    private int destinationY;
    private int destinationZ;

    private boolean pvpFlag;

    private Map<Integer, PlayerInstance> nearbyPlayers;


    public PlayerInstance(int _id, L2Character _character, GameClientChannelHandler _network) {
        this.id = _id;
        this.character = _character;
        this.network = _network;

        this.nearbyPlayers = new HashMap<>();

        this.title = "";

        this.hp = 100;
        this.mp = 100;
        this.inventoryLoad = 0;

        this.x = 46934;
        this.y = 51467;
        this.z = -2927; // -2977;
        this.heading = 0;

        this.pvpFlag = false;
    }


    public L2Character getCharacter() {
        return this.character;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.character.getLogin();
    }

    public String getTitle() {
        return this.title;
    }

    public int getClanId() {
        return 0;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getZ() {
        return this.z;
    }

    public int getHeading() {
        return this.heading;
    }

    public void setCoordinates(int _x, int _y, int _z, int _heading) {
        this.x = _x;
        this.y = _y;
        this.z = _z;
        this.heading = _heading;
    }

    public void setDestinationCoordinates(int _x, int _y, int _z) {
        this.destinationX = _x;
        this.destinationY = _y;
        this.destinationZ = _z;
    }

    public int getCurrentHP() {
        return this.hp;
    }

    public int getMaxHP() {
        return 100;
    }

    public int getCurrentMP() {
        return this.mp;
    }

    public int getMaxMP() {
        return 100;
    }

    public int getCurrentSP() {
        return 100;
    }


    public int getMaxCP() {
        return 100;
    }

    public int getCurrentCP() {
        return 0;
    }

    public int getInventoryLoad() {
        return this.inventoryLoad;
    }

    public int getMaxInventoryLoad() {
        return 100;
    }

    public int getExp() {
        return 10;
    }

    public int getLevel() {
        return 1;
    }

    public int getKarma() {
        return 0;
    }

    public int getPKKills() {
        return 0;
    }

    public int getPVPKills() {
        return 0;
    }

    public boolean isPvPFlag() {
        return this.pvpFlag;
    }

    public int getINT() {
        return this.character.getTemplate().getBaseINT();
    }

    public int getSTR() {
        return this.character.getTemplate().getBaseSTR();
    }

    public int getCON() {
        return this.character.getTemplate().getBaseCON();
    }

    public int getMEN() {
        return this.character.getTemplate().getBaseMEN();
    }

    public int getDEX() {
        return this.character.getTemplate().getBaseDEX();
    }

    public int getWIT() {
        return this.character.getTemplate().getBaseWIT();
    }

    public int getPAtk() {
        return 10;
    }

    public int getPAtkSpd() {
        return 1;
    }

    public int getPDef() {
        return 10;
    }

    public int getEvasionRate() {
        return 1;
    }

    public int getAccuracy() {
        return 10;
    }

    public int getCriticalHit() {
        return 2;
    }

    public int getMAtk() {
        return 10;
    }

    public int getMAtkSpd() {
        return 10;
    }

    public int getMDef() {
        return 10;
    }

    public int getRunSpd() {
        return 250;
    }

    public int getWalkSpd() {
        return 180;
    }

    public int getSwimRunSpd() {
        return 50;
    }

    public int getSwimWalkSpd() {
        return 20;
    }

    public int getFlyRunSpd() {
        return 0;
    }

    public int getFlyWalkSpd() {
        return 0;
    }

    public double getMoveMultiplier() {
        return 1.00;
    }

    public double getAtkSpdMultiplier() {
        return 1;
    }

    public double getCollisionRadius() {
        return 6.5;
    }

    public double getCollisionHeight() {
        return 25;
    }

    public void addNearbyPlayer(PlayerInstance _player) {
        this.nearbyPlayers.put(_player.getId(), _player);
    }

    public void sendPacket(OutgoingGameClientPacketInterface _packet) {
        this.network.sendPacket(_packet);
    }

    public void broadcast(OutgoingGameClientPacketInterface _packet) {
        this.network.sendPacket(_packet);

        for (PlayerInstance player : this.nearbyPlayers.values()) {
            player.sendPacket(_packet);
        }
    }
}
