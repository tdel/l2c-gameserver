package application.controller.gameclient.ingame;

import application.model.entity.template.SkillLevelTemplate;
import application.model.instance.PlayerInstance;
import kernel.network.gameclient.GameClientChannelHandler;
import kernel.network.gameclient.packets.IncomingGameClientPacketInterface;
import kernel.network.gameclient.packets.PacketReader;
import application.view.gameclient.ingame.MagicSkillUse;

public class RequestMagicSkillUse implements IncomingGameClientPacketInterface {

    @Override
    public void execute(PacketReader _reader, GameClientChannelHandler _client) {

        int magicId = _reader.readD();
        boolean isForceAttack = _reader.readD() != 0;
        boolean isShiftPressed = _reader.readC() != 0;


        PlayerInstance player = _client.getPlayer();
        SkillLevelTemplate skill = player.getSkills().iterator().next();

        player.broadcast(new MagicSkillUse(player, player, skill));


    }
}
