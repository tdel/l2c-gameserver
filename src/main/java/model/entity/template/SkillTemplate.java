package model.entity.template;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(name = "template_skill")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class SkillTemplate {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private boolean isPassive;

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public boolean isPassive() {
        return this.isPassive;
    }

    public boolean isActive() {
        return !this.isPassive;
    }

}
