package application.view.gameclient.ingame;

import application.model.entity.Inventory;
import application.model.instance.PlayerInstance;
import kernel.network.gameclient.packets.OutgoingGameClientPacketInterface;
import kernel.network.gameclient.packets.PacketWriter;

public class CharacterInfo implements OutgoingGameClientPacketInterface {

    private PlayerInstance player;

    private static final int[] PAPERDOLL_ORDER = new int[] {
                    Inventory.PAPERDOLL_UNDER,
                    Inventory.PAPERDOLL_HEAD,
                    Inventory.PAPERDOLL_RHAND,
                    Inventory.PAPERDOLL_LHAND,
                    Inventory.PAPERDOLL_GLOVES,
                    Inventory.PAPERDOLL_CHEST,
                    Inventory.PAPERDOLL_LEGS,
                    Inventory.PAPERDOLL_FEET,
                    Inventory.PAPERDOLL_CLOAK,
                    Inventory.PAPERDOLL_RHAND,
                    Inventory.PAPERDOLL_HAIR,
                    Inventory.PAPERDOLL_HAIR2,
                    Inventory.PAPERDOLL_RBRACELET,
                    Inventory.PAPERDOLL_LBRACELET,
                    Inventory.PAPERDOLL_DECO1,
                    Inventory.PAPERDOLL_DECO2,
                    Inventory.PAPERDOLL_DECO3,
                    Inventory.PAPERDOLL_DECO4,
                    Inventory.PAPERDOLL_DECO5,
                    Inventory.PAPERDOLL_DECO6,
                    Inventory.PAPERDOLL_BELT
            };


    public CharacterInfo(PlayerInstance _player) {
        this.player = _player;
    }

    @Override
    public void write(PacketWriter _writer) {

        _writer.writeC(0x31);

        _writer.writeD(this.player.getCoordinate().getX());
        _writer.writeD(this.player.getCoordinate().getY());
        _writer.writeD(this.player.getCoordinate().getZ());
        _writer.writeD(0); // vehiculeid);

        _writer.writeD(this.player.getId());
        _writer.writeS(this.player.getName());
        _writer.writeD(this.player.getCharacter().getTemplate().getRace().getId());
        _writer.writeD(this.player.getCharacter().getAppearence().getSex().getId());
        _writer.writeD(this.player.getCharacter().getId());

        for (int slot : PAPERDOLL_ORDER) {
            _writer.writeD(0x00);  // item display Id !
        }

        for (int slot : PAPERDOLL_ORDER) {
            _writer.writeD(0x00);  // augmentationId
        }


        _writer.writeD(0); // talisman slots
        _writer.writeD(0); // cloack

        _writer.writeD(this.player.isPvPFlag() ? 1 : 0);
        _writer.writeD(this.player.getKarma());

        _writer.writeD(this.player.getMAtkSpd());
        _writer.writeD(this.player.getPAtkSpd());

        _writer.writeD(0x00);

        _writer.writeD(this.player.getRunSpd());
        _writer.writeD(this.player.getWalkSpd());
        _writer.writeD(this.player.getSwimRunSpd());
        _writer.writeD(this.player.getSwimWalkSpd());
        _writer.writeD(this.player.getFlyRunSpd());
        _writer.writeD(this.player.getFlyWalkSpd());
        _writer.writeD(this.player.getFlyRunSpd());
        _writer.writeD(this.player.getFlyWalkSpd());
        _writer.writeF(this.player.getMoveMultiplier());
        _writer.writeF(this.player.getAtkSpdMultiplier());

        _writer.writeF(this.player.getCollisionRadius());
        _writer.writeF(this.player.getCollisionHeight());

        _writer.writeD(this.player.getCharacter().getAppearence().getHairStyle());
        _writer.writeD(this.player.getCharacter().getAppearence().getHairColor());
        _writer.writeD(this.player.getCharacter().getAppearence().getFace());

        _writer.writeS(this.player.getTitle());

        _writer.writeD(0x00); // clan & ally
        _writer.writeD(0x00);
        _writer.writeD(0x00);
        _writer.writeD(0x00);


        _writer.writeC(this.player.isStanding() ? 1 : 0); // sit
        _writer.writeC(this.player.isRunning() ? 1 : 0); // running
        _writer.writeC(this.player.isInCombat() ? 1 : 0); // in combat ?

        _writer.writeC(0); // is dead
        _writer.writeC(this.player.isInvisible() ? 1 : 0); // is invi

        _writer.writeC(0); // mount 1-on Strider, 2-on Wyvern, 3-on Great Wolf, 0-no mount
        _writer.writeC(0); // private store id

        _writer.writeH(0); // cubic size

        _writer.writeC(0); // in party match room
        _writer.writeD(0); // abnormal

        _writer.writeC(0); // water / fly / ground
        _writer.writeH(0); // name recommandation
        _writer.writeD(1000000);// mount npc id;
        _writer.writeD(this.player.getCharacter().getTemplate().getClassId()); // classId
        _writer.writeD(0x00);
        _writer.writeC(0); // enchant effect

        _writer.writeC(0); // team id ?

        _writer.writeD(0); // clan crest
        _writer.writeC(0); // noble
        _writer.writeC(0); // hero

        _writer.writeC(0); // fishing
        _writer.writeD(0); // fishing x
        _writer.writeD(0); // fishing y
        _writer.writeD(0); // fishing z

        _writer.writeD(this.player.getNameColor()); // name color
        _writer.writeD(this.player.getHeading());

        _writer.writeD(0); // pledge class
        _writer.writeD(0); // pledge type

        _writer.writeD(0); // title color
        _writer.writeD(0); // cursed weapon

        _writer.writeD(0); // clan reputation score

        _writer.writeD(0); // transformation ID
        _writer.writeD(0); // agathion id

        _writer.writeD(0x01);

        _writer.writeD(0); // abnormal visual effects





    }


}
