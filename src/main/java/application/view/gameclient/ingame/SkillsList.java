package application.view.gameclient.ingame;

import application.model.entity.template.SkillLevelTemplate;
import kernel.network.gameclient.packets.OutgoingGameClientPacketInterface;
import kernel.network.gameclient.packets.PacketWriter;

import java.util.Set;

public class SkillsList implements OutgoingGameClientPacketInterface {

    private Set<SkillLevelTemplate> skills;

    public SkillsList(Set<SkillLevelTemplate> _skills) {
        this.skills = _skills;
    }


    @Override
    public void write(PacketWriter _writer) {
        _writer.writeC(0x5F);

        _writer.writeD(this.skills.size());

        for (SkillLevelTemplate skill : this.skills) {
            _writer.writeD(skill.getSkill().isPassive() ? 1 : 0);
            _writer.writeD(skill.getLevel());
            _writer.writeD(skill.getSkill().getId());
            _writer.writeC(0); // disabled
            _writer.writeC(0); // enchanted
        }


    }
}
