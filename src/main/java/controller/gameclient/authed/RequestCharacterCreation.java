package controller.gameclient.authed;

import com.google.inject.Inject;
import model.entity.L2Character;
import model.entity.embeddable.CharacterAppearence;
import model.entity.enumerate.CharacterSex;
import model.entity.template.CharacterTemplate;
import model.repository.Entity.L2CharacterRepository;
import model.repository.template.CharacterTemplateRepository;
import network.gameclient.GameClientChannelHandler;
import network.gameclient.packets.IncomingGameClientPacketInterface;
import network.gameclient.packets.PacketReader;
import view.gameclient.connected.CharacterCreationOK;

public class RequestCharacterCreation implements IncomingGameClientPacketInterface {

    private final CharacterTemplateRepository ctRepository;
    private final L2CharacterRepository charRepository;

    @Inject
    public RequestCharacterCreation(CharacterTemplateRepository _ctRepository, L2CharacterRepository _charRepository) {
        this.ctRepository = _ctRepository;
        this.charRepository = _charRepository;
    }

    @Override
    public void execute(PacketReader _reader, GameClientChannelHandler _client) {

        String name = _reader.readS();
        int race = _reader.readD();
        byte sex = (byte) _reader.readD();
        int classId = _reader.readD();
        int statInt = _reader.readD();
        int statStr = _reader.readD();
        int statCon = _reader.readD();
        int statMen = _reader.readD();
        int statDex = _reader.readD();
        int statWit = _reader.readD();
        byte hairStyle = (byte) _reader.readD();
        byte hairColor = (byte) _reader.readD();
        byte face = (byte) _reader.readD();


        // Last Verified: May 30, 2009 - Gracia Final - Players are able to create characters with names consisting of as little as 1,2,3 letter/number combinations.
        if ((name.length() < 1) || (name.length() > 16)) {
            //_client.sendPacket(new CharCreateFail(CharCreateFail.REASON_16_ENG_CHARS));
            return;
        }

        CharacterTemplate charTemplate = this.ctRepository.findOneById(classId);
        System.out.println("id : " + classId);
        System.out.println(charTemplate.getBaseDEX());

        CharacterAppearence charAppearence = new CharacterAppearence();
        charAppearence.setFace(face);
        charAppearence.setHairStyle(hairStyle);
        charAppearence.setHairColor(hairColor);
        charAppearence.setSex(CharacterSex.fromByte(sex));

        L2Character l2char = new L2Character();
        l2char.setTemplate(charTemplate);
        l2char.setAppearence(charAppearence);
        l2char.setAccount(1);
        l2char.setLogin(name);



        this.charRepository.save(l2char);

        _client.sendPacket(new CharacterCreationOK());
    }
}
