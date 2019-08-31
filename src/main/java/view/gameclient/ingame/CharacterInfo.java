package view.gameclient.ingame;

import model.entity.Inventory;
import model.entity.instance.PlayerInstance;
import network.gameclient.packets.OutgoingGameClientPacketInterface;
import network.gameclient.packets.PacketWriter;

public class CharacterInfo implements OutgoingGameClientPacketInterface {

    private PlayerInstance character;

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
        this.character = _player;
    }

    @Override
    public void write(PacketWriter _writer) {

        _writer.writeC(0x31);

        _writer.writeD(this.character.getX());
        _writer.writeD(this.character.getY());
        _writer.writeD(this.character.getZ());
        _writer.writeD(0); // vehiculeid);

        _writer.writeD(this.character.getId());
        _writer.writeS(this.character.getName());
        _writer.writeD(this.character.getCharacter().getTemplate().getRace().getId());
        _writer.writeD(this.character.getCharacter().getAppearence().getSex().ordinal());
        _writer.writeD(this.character.getCharacter().getId());

        for (int slot : PAPERDOLL_ORDER) {
            _writer.writeD(0x00);  // item display Id !
        }

        for (int slot : PAPERDOLL_ORDER) {
            _writer.writeD(0x00);  // augmentationId
        }


        _writer.writeD(0); // talisman slots
        _writer.writeD(0); // cloack

        _writer.writeD(this.character.isPvPFlag() ? 1 : 0);
        _writer.writeD(this.character.getKarma());

        _writer.writeD(this.character.getMAtkSpd());
        _writer.writeD(this.character.getPAtkSpd());

        _writer.writeD(0x00);

        _writer.writeD(this.character.getRunSpd());
        _writer.writeD(this.character.getWalkSpd());
        _writer.writeD(this.character.getSwimRunSpd());
        _writer.writeD(this.character.getSwimWalkSpd());
        _writer.writeD(this.character.getFlyRunSpd());
        _writer.writeD(this.character.getFlyWalkSpd());
        _writer.writeD(this.character.getFlyRunSpd());
        _writer.writeD(this.character.getFlyWalkSpd());
        _writer.writeF(this.character.getMoveMultiplier());
        _writer.writeF(this.character.getAtkSpdMultiplier());

        _writer.writeF(this.character.getCollisionRadius());
        _writer.writeF(this.character.getCollisionHeight());

        _writer.writeD(this.character.getCharacter().getAppearence().getHairStyle());
        _writer.writeD(this.character.getCharacter().getAppearence().getHairColor());
        _writer.writeD(this.character.getCharacter().getAppearence().getFace());

        _writer.writeS(this.character.getTitle());

        _writer.writeD(0x00); // clan & ally
        _writer.writeD(0x00);
        _writer.writeD(0x00);
        _writer.writeD(0x00);


        _writer.writeC(1); // sit
        _writer.writeC(1); // running
        _writer.writeC(0); // in combat ?

        _writer.writeC(0); // is dead
        _writer.writeC(0); // is invi

        _writer.writeC(0); // mount 1-on Strider, 2-on Wyvern, 3-on Great Wolf, 0-no mount
        _writer.writeC(0); // private store id

        _writer.writeH(0); // cubic size

        _writer.writeC(0); // in party match room
        _writer.writeD(0); // abnormal

        _writer.writeC(0); // water / fly / ground
        _writer.writeH(0); // name recommandation
        _writer.writeD(1000000);// mount npc id;
        _writer.writeD(this.character.getCharacter().getId()); // classId
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

        _writer.writeD(0); // name color
        _writer.writeD(this.character.getHeading());

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
