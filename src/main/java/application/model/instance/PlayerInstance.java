package application.model.instance;

import application.model.entity.L2Character;
import application.model.entity.template.SkillLevelTemplate;
import application.model.service.World;
import kernel.network.gameclient.GameClientChannelHandler;
import kernel.network.gameclient.packets.OutgoingGameClientPacketInterface;
import application.view.gameclient.ingame.ActionFail;
import application.view.gameclient.ingame.MoveToLocation;
import application.view.gameclient.ingame.StopMove;

import java.util.Map;
import java.util.Set;

public class PlayerInstance {

    private final World world;

    private int id;
    private GameClientChannelHandler network;
    private L2Character character;

    private String title;

    private int hp;
    private int mp;
    private int inventoryLoad;

    private Coordinate coordinate;
    private Coordinate destination;

    private boolean pvpFlag;

    public PlayerInstance(World _world, int _id, L2Character _character, GameClientChannelHandler _network) {
        this.world =_world;

        this.id = _id;
        this.character = _character;
        this.network = _network;

        this.title = "";

        this.hp = 100;
        this.mp = 100;
        this.inventoryLoad = 0;

        this.coordinate = new Coordinate(46934, 51467, -2927, 0);

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

    public int getNameColor() {
        return 255; // white ?
    }

    public String getTitle() {
        return this.title;
    }

    public int getTitleColor() {
        return 255;
    }

    public int getClanId() {
        return 0;
    }

    public Coordinate getCoordinate() {
        return this.coordinate;
    }

    public int getHeading() {
        return this.coordinate.getHeading();
    }

    public void setCoordinates(int _x, int _y, int _z, int _heading) {
        this.coordinate.setCoordinate(_x, _y, _z, _heading);
    }

    public boolean isInRange(Coordinate _coordinate, int _radius) {
        return true;
        //return this.coordinate.calculateDistance3D(_coordinate) <= _radius;
    }

    public void setDestinationCoordinates(int _x, int _y, int _z) {
        //targetZ += player.getCollisionHeight(); // geodata ?

        this.destination = new Coordinate(_x, _y, _z);

        if (this.coordinate.isSameCoordinate(_x, _y, _z)) {
            this.sendPacket(new StopMove(this.getId(), this.getCoordinate()));
            this.sendPacket(new ActionFail());

            return;
        }

        this.broadcast(new MoveToLocation(this.getId(), this.getCoordinate(), this.destination));
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


    public boolean isStanding() {
        return true;
    }

    public boolean isRunning() {
        return true;
    }

    public boolean isInCombat() {
        return false;
    }

    public boolean isInvisible() {
        return false;
    }

    public Set<SkillLevelTemplate> getSkills() {
        return this.character.getSkills();
    }

    public void sendPacket(OutgoingGameClientPacketInterface _packet) {
        this.network.sendPacket(_packet);
    }

    public void broadcast(OutgoingGameClientPacketInterface _packet) {
        this.network.sendPacket(_packet);

        Map<Integer, PlayerInstance> playersInRange = this.world.getPlayersInRange(this, 3600);

        for (PlayerInstance player : playersInRange.values()) {
            player.sendPacket(_packet);
        }
    }
}
