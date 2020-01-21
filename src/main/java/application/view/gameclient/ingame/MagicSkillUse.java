package application.view.gameclient.ingame;

import application.model.entity.template.SkillLevelTemplate;
import application.model.instance.PlayerInstance;
import kernel.network.gameclient.packets.OutgoingGameClientPacketInterface;
import kernel.network.gameclient.packets.PacketWriter;

public class MagicSkillUse implements OutgoingGameClientPacketInterface {

    private PlayerInstance caster;

    private PlayerInstance target;

    private SkillLevelTemplate skill;


    public MagicSkillUse(PlayerInstance _caster, PlayerInstance _target, SkillLevelTemplate _skill) {
        this.caster = _caster;
        this.target = _target;
        this.skill = _skill;
    }

    @Override
    public void write(PacketWriter _writer) {
        _writer.writeC(0x48);
        _writer.writeD(this.caster.getId());
        _writer.writeD(this.target.getId());
        _writer.writeD(this.skill.getSkill().getId());
        _writer.writeD(this.skill.getLevel());
        _writer.writeD(3); // hit time
        _writer.writeD(5); // reuse delay
        _writer.writeD(this.caster.getCoordinate().getX());
        _writer.writeD(this.caster.getCoordinate().getY());
        _writer.writeD(this.caster.getCoordinate().getZ());

        _writer.writeD(0); // unknown


        _writer.writeH(0); // ground location ?
       // _writer.writeD(0); // ground location ?
       // _writer.writeD(0); // ground location ?
       // _writer.writeD(0); // ground location ?

        _writer.writeD(this.target.getCoordinate().getX());
        _writer.writeD(this.target.getCoordinate().getY());
        _writer.writeD(this.target.getCoordinate().getZ());
    }
}
