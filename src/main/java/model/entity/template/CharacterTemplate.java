package model.entity.template;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(name = "template_character")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class CharacterTemplate {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="fk_race_id", nullable=false)
    private RaceTemplate race;

    @Column(unique = true)
    private String name;

    @Column(name="base_str")
    private int baseStr;

    @Column(name="base_dex")
    private int baseDex;

    @Column(name="base_con")
    private int baseCon;

    @Column(name="base_int")
    private int baseInt;

    @Column(name="base_wit")
    private int baseWit;

    @Column(name="base_men")
    private int baseMen;


    public int getId() {
        return this.id;
    }

    public RaceTemplate getRace() {
        return this.race;
    }

    public int getBaseSTR() {
        return this.baseStr;
    }

    public int getBaseDEX() {
        return this.baseDex;
    }

    public int getBaseCON() {
        return this.baseCon;
    }

    public int getBaseINT() {
        return this.baseInt;
    }

    public int getBaseWIT() {
        return this.baseWit;
    }

    public int getBaseMEN() {
        return this.baseMen;
    }

}
