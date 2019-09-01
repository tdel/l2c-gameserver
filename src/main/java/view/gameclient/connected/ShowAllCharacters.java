package view.gameclient.connected;

import model.entity.Inventory;
import model.entity.L2Character;
import model.entity.embeddable.CharacterAppearence;
import network.gameclient.packets.OutgoingGameClientPacketInterface;
import network.gameclient.packets.PacketWriter;

import java.util.List;

public class ShowAllCharacters implements OutgoingGameClientPacketInterface {

    private List<L2Character> characters;

    public ShowAllCharacters(List<L2Character> _characters) {
        this.characters = _characters;
    }

    @Override
    public void write(PacketWriter _writer) {
        _writer.writeC(0x09);
        _writer.writeD(this.characters.size()); // number of characters

        _writer.writeD(2); // maximum number of chars allowed
        _writer.writeC(0x00);

        for (L2Character l2char : this.characters) {
            _writer.writeS(l2char.getLogin());
            _writer.writeD(l2char.getId());

            _writer.writeS("loginName");
            _writer.writeD(1); // sessionId;

            _writer.writeD(0); // clanId
            _writer.writeD(0x00); // builder level ?

            CharacterAppearence appearence = l2char.getAppearence();
            _writer.writeD(appearence.getSex().ordinal()); // sex
            _writer.writeD(l2char.getTemplate().getRace().getId()); // raceid
            _writer.writeD(l2char.getTemplate().getClassId()); // base classid

            _writer.writeD(0x01); // gameservername

            _writer.writeD(0); // pos X
            _writer.writeD(0); // pos Y
            _writer.writeD(0); // pos Z

            _writer.writeF(0.00); // current HP
            _writer.writeF(0.00); // current MP
            _writer.writeD(0); // current SP
            _writer.writeQ(0); // current exp
            _writer.writeF(100); // exp ??

            _writer.writeD(1); // level
            _writer.writeD(0); // karma
            _writer.writeD(0); // pk kills count
            _writer.writeD(0); // pvp kills count

            _writer.writeD(0x00);
            _writer.writeD(0x00);
            _writer.writeD(0x00);
            _writer.writeD(0x00);
            _writer.writeD(0x00);
            _writer.writeD(0x00);
            _writer.writeD(0x00);

            // inventory slot
            for (int slot : Inventory.PAPERDOLL_ORDER) {
                _writer.writeD(0x00);  // item Id !
            }

            _writer.writeD(appearence.getHairStyle());
            _writer.writeD(appearence.getHairColor());
            _writer.writeD(appearence.getFace());

            _writer.writeF(100); // max HP
            _writer.writeF(100); // max MP

            _writer.writeD(0); // delete timer
            _writer.writeD(l2char.getTemplate().getClassId()); // classid actuel, pas la base
            _writer.writeD(0x00); // activeId ? pour le mettre en premier plan je pense

            _writer.writeC(0); // enchant level
            _writer.writeD(0); // augmentation ID

            _writer.writeD(0x00); // transformation ID

            _writer.writeD(0x00); // pet NPC
            _writer.writeD(0x00); // pet level
            _writer.writeD(0x00); // pet food
            _writer.writeD(0x00); // pet food level
            _writer.writeD(0x00); // current pet HP
            _writer.writeD(0x00); // current pet MP

            _writer.writeD(0x00); // vitality points

        }

    }
}
