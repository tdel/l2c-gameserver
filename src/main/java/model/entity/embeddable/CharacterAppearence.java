package model.entity.embeddable;

import model.entity.enumerate.CharacterSex;

import javax.persistence.*;

@Embeddable
public class CharacterAppearence {

    @Column
    private byte face;

    @Column(name="hair_color")
    private byte hairColor;

    @Column(name="hair_style")
    private byte hairStyle;

    @Enumerated
    @Column(columnDefinition = "smallint")
    private CharacterSex sex; // female = true (1)


    public byte getFace() {
        return this.face;
    }
    public void setFace(byte _face) {
        this.face = _face;
    }

    public byte getHairColor() {
        return this.hairColor;
    }
    public void setHairColor(byte _hairColor) {
        this.hairColor = _hairColor;
    }

    public byte getHairStyle() {
        return this.hairStyle;
    }
    public void setHairStyle(byte _hairStyle) {
        this.hairStyle = _hairStyle;
    }

    public CharacterSex getSex() {
        return this.sex;
    }

    public void setSex(CharacterSex _sex) {
        this.sex = _sex;
    }

}
