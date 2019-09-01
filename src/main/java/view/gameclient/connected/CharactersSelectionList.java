package view.gameclient.connected;

import model.entity.template.CharacterTemplate;
import network.gameclient.packets.OutgoingGameClientPacketInterface;
import network.gameclient.packets.PacketWriter;

import java.util.List;

public class CharactersSelectionList implements OutgoingGameClientPacketInterface {

    private List<CharacterTemplate> charsTemplate;

    public CharactersSelectionList(List<CharacterTemplate> _charsTemplate) {
        this.charsTemplate = _charsTemplate;
    }

    @Override
    public void write(PacketWriter _writer) {
        _writer.writeC(0x0D);

        _writer.writeD(0);
        for (CharacterTemplate charTemplate : this.charsTemplate) {
            _writer.writeD(charTemplate.getRace().getId());
            _writer.writeD(charTemplate.getClassId());

            _writer.writeD(0x46);
            _writer.writeD(charTemplate.getBaseSTR());
            _writer.writeD(0x0A);

            _writer.writeD(0x46);
            _writer.writeD(charTemplate.getBaseDEX());
            _writer.writeD(0x0A);

            _writer.writeD(0x46);
            _writer.writeD(charTemplate.getBaseCON());
            _writer.writeD(0x0A);

            _writer.writeD(0x46);
            _writer.writeD(charTemplate.getBaseINT());
            _writer.writeD(0x0A);

            _writer.writeD(0x46);
            _writer.writeD(charTemplate.getBaseWIT());
            _writer.writeD(0x0A);

            _writer.writeD(0x46);
            _writer.writeD(charTemplate.getBaseMEN());
            _writer.writeD(0x0A);
        }

    }
}
