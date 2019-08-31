package model.entity.instance;

import model.entity.L2Character;
import network.gameclient.GameClientChannelHandler;
import network.gameclient.packets.OutgoingGameClientPacketInterface;

public class PlayerInstance {

    private int id;
    private GameClientChannelHandler network;
    private L2Character character;


    public PlayerInstance(int _id, L2Character _character, GameClientChannelHandler _network) {
        this.id = _id;
        this.character = _character;
        this.network = _network;
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
        return "";
    }

    public int getClanId() {
        return 0;
    }

    public int getX() {
        return -81447;
    }

    public int getY() {
        return 152760;
    }

    public int getZ() {
        return -3170;
    }

    public int getHeading() {
        return 0;
    }

    public int getCurrentHP() {
        return 100;
    }

    public int getCurrentMP() {
        return 100;
    }

    public int getCurrentSP() {
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
        return 120;
    }

    public int getWalkSpd() {
        return 90;
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
        return 100;
    }

    public double getCollisionHeight() {
        return 100;
    }

    public void sendPacket(OutgoingGameClientPacketInterface _packet) {
        this.network.sendPacket(_packet);
    }

}
