package view.gameclient.authed;

import model.entity.instance.PlayerInstance;
import network.gameclient.packets.OutgoingGameClientPacketInterface;
import network.gameclient.packets.PacketWriter;

public class CharacterSelected implements OutgoingGameClientPacketInterface {

    private PlayerInstance player;
    private int sessionId;

    public CharacterSelected(PlayerInstance _player, int _sessionId) {
        this.player = _player;
        this.sessionId = _sessionId;
    }

    @Override
    public void write(PacketWriter _writer) {
        _writer.writeC(0x0B);


        _writer.writeS(this.player.getName());
        _writer.writeD(this.player.getId());
        _writer.writeS(this.player.getTitle());
        _writer.writeD(this.sessionId);
        _writer.writeD(this.player.getClanId()); // getClan().getId();

        _writer.writeD(0x00); // ?
        _writer.writeD(this.player.getCharacter().getAppearence().getSex().ordinal());
        _writer.writeD(this.player.getCharacter().getTemplate().getRace().getId()); // race
        _writer.writeD(this.player.getCharacter().getTemplate().getId());
        _writer.writeD(0x01); // active ?
        _writer.writeD(this.player.getX());
        _writer.writeD(this.player.getY());
        _writer.writeD(this.player.getZ());

        _writer.writeF(this.player.getCurrentHP());
        _writer.writeF(this.player.getCurrentMP());
        _writer.writeD(this.player.getCurrentSP());

        _writer.writeQ(this.player.getExp());
        _writer.writeD(this.player.getLevel());
        _writer.writeD(this.player.getKarma());
        _writer.writeD(this.player.getPKKills());

        _writer.writeD(this.player.getINT());
        _writer.writeD(this.player.getSTR());
        _writer.writeD(this.player.getCON());
        _writer.writeD(this.player.getMEN());
        _writer.writeD(this.player.getDEX());
        _writer.writeD(this.player.getWIT());

        _writer.writeD(0); // Game time !
        _writer.writeD(0x00);

        _writer.writeD(this.player.getCharacter().getTemplate().getId());

        _writer.writeD(0x00);
        _writer.writeD(0x00);
        _writer.writeD(0x00);
        _writer.writeD(0x00);

        _writer.writeB(new byte[64]);
        _writer.writeD(0x00);



    }
}
