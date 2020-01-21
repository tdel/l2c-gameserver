package application.model.entity.embeddable;

import application.model.entity.template.SexTemplate;

import javax.persistence.*;

@Embeddable
public class CharacterAppearence {

    @Column
    private byte face;

    @Column(name="hair_color")
    private byte hairColor;

    @Column(name="hair_style")
    private byte hairStyle;

    @ManyToOne
    @JoinColumn(name = "fk_sex_id", referencedColumnName = "id")
    private SexTemplate sex;


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

    public SexTemplate getSex() {
        return this.sex;
    }

    public void setSex(SexTemplate _sex) {
        this.sex = _sex;
    }

}
