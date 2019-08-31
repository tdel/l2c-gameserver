package view.gameclient.ingame;

import model.entity.Inventory;
import model.entity.instance.PlayerInstance;
import network.gameclient.packets.OutgoingGameClientPacketInterface;
import network.gameclient.packets.PacketWriter;

public class PlayerInfo implements OutgoingGameClientPacketInterface {

    private PlayerInstance player;

    public PlayerInfo(PlayerInstance _player) {
        this.player = _player;
    }

    @Override
    public void write(PacketWriter _writer) {
        _writer.writeC(0x32);

        _writer.writeD(this.player.getX());
        _writer.writeD(this.player.getY());
        _writer.writeD(this.player.getZ());
        _writer.writeD(0); // vehicule

        _writer.writeD(this.player.getId());
        _writer.writeS(this.player.getName());
        _writer.writeD(this.player.getCharacter().getTemplate().getRace().getId());
        _writer.writeD(this.player.getCharacter().getAppearence().getSex().ordinal());

        _writer.writeD(this.player.getCharacter().getTemplate().getId());

        _writer.writeD(this.player.getLevel());
        _writer.writeQ(this.player.getExp());

        _writer.writeF(1000); // exp needed ?

        _writer.writeD(this.player.getSTR());
        _writer.writeD(this.player.getDEX());
        _writer.writeD(this.player.getCON());
        _writer.writeD(this.player.getINT());
        _writer.writeD(this.player.getWIT());
        _writer.writeD(this.player.getMEN());
        _writer.writeD(1000); // maxHP
        _writer.writeD(this.player.getCurrentHP());
        _writer.writeD(1000); // max MP
        _writer.writeD(this.player.getCurrentMP());
        _writer.writeD(this.player.getCurrentSP());
        _writer.writeD(0); // current Load
        _writer.writeD(10000); // max load

        _writer.writeD(20); // 20 no weapon equiped, 40 equipped

        for (int slot : Inventory.PAPERDOLL_ORDER) {
            _writer.writeD(0x00);  // object id
        }

        for (int slot : Inventory.PAPERDOLL_ORDER) {
            _writer.writeD(0x00);  // item display Id !
        }

        for (int slot : Inventory.PAPERDOLL_ORDER) {
            _writer.writeD(0x00);  // augmentationId
        }

        _writer.writeD(0); // talisman slot
        _writer.writeD(0); // equip cloak
        _writer.writeD(this.player.getPAtk());
        _writer.writeD(this.player.getPAtkSpd());
        _writer.writeD(this.player.getPDef());
        _writer.writeD(this.player.getEvasionRate());
        _writer.writeD(this.player.getAccuracy());
        _writer.writeD(this.player.getCriticalHit());
        _writer.writeD(this.player.getMAtk());
        _writer.writeD(this.player.getMAtkSpd());
        _writer.writeD(this.player.getPAtkSpd());
        _writer.writeD(this.player.getMDef());
        _writer.writeD(0);// pvp flag
        _writer.writeD(this.player.getKarma());

        _writer.writeD(this.player.getRunSpd());
        _writer.writeD(this.player.getWalkSpd());
        _writer.writeD(this.player.getSwimRunSpd());
        _writer.writeD(this.player.getSwimWalkSpd());
        _writer.writeD(this.player.getFlyRunSpd());
        _writer.writeD(this.player.getFlyWalkSpd());
        _writer.writeF(this.player.getMoveMultiplier());
        _writer.writeF(this.player.getAtkSpdMultiplier());

        _writer.writeF(this.player.getCollisionRadius());
        _writer.writeF(this.player.getCollisionHeight());

        _writer.writeD(this.player.getCharacter().getAppearence().getHairStyle());
        _writer.writeD(this.player.getCharacter().getAppearence().getHairColor());
        _writer.writeD(this.player.getCharacter().getAppearence().getFace());
        _writer.writeD(0); // is GM

        _writer.writeS(this.player.getTitle());
        _writer.writeD(this.player.getClanId());
        _writer.writeD(0); // clan crest
        _writer.writeD(0); // allyid
        _writer.writeD(0); // ally crest
        _writer.writeD(0); // relation
        _writer.writeC(0);// mountType
        _writer.writeC(0); // private store
        _writer.writeC(0); // dwarven craft
        _writer.writeD(this.player.getPKKills());
        _writer.writeD(this.player.getPVPKills());

        _writer.writeH(0); // cubic size


        _writer.writeC(0); // party matching
        _writer.writeD(0); // stealth
        _writer.writeD(0); // clan priviledge

        _writer.writeH(0); // recommandation left
        _writer.writeH(0); // recommandation have
        _writer.writeD(0); // mount npc id
        _writer.writeH(120); // inventory limit;

        _writer.writeD(this.player.getCharacter().getId());
        _writer.writeD(0x00); //  special effects? circles around player...
        _writer.writeD(10000); // max CP
        _writer.writeD(10); // current CP;
        _writer.writeC(0); // mounted ?

        _writer.writeC(0); // team ID

        _writer.writeD(0); // clan crest large id
        _writer.writeC(0); // is noble
        _writer.writeC(0); // is hero
        _writer.writeC(0); // is fishing
        _writer.writeD(0); // fish X
        _writer.writeD(0); // fish y
        _writer.writeD(0); // fish z
        _writer.writeD(255); // color name

        _writer.writeC(0); // running ?
        _writer.writeD(0);// pledgeclass
        _writer.writeD(0); // pdledge type
        _writer.writeD(255); // title color

        _writer.writeD(0); // cursed !

        _writer.writeD(0); // transfo id

        _writer.writeH(0);  // attack attributes
        _writer.writeH(0); // attack element
        _writer.writeH(0); // attack element
        _writer.writeH(0); // attack element
        _writer.writeH(0); // attack element
        _writer.writeH(0); // attack element
        _writer.writeH(0); // attack element
        _writer.writeH(0); // attack element

        _writer.writeD(0); //aghation id

        _writer.writeD(0); // fame
        _writer.writeD(0); // minimap on hellbound
        _writer.writeD(20); // vitality points
        _writer.writeD(0); // abnormal visual effect






    }
}
