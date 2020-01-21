package application.model.entity.template;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(name = "template_skill_level")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class SkillLevelTemplate {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="fk_skill_id", referencedColumnName="id")
    private SkillTemplate skill;

    @Column
    private int level;

    @Column(name="cost_sp")
    private int costSp;


    public int getId() {
        return this.id;
    }

    public SkillTemplate getSkill() {
        return this.skill;
    }

    public int getLevel() {
        return this.level;
    }

    public int getCostSp() {
        return this.costSp;
    }

}
