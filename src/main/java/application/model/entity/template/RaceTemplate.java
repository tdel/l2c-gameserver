package application.model.entity.template;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(name = "template_race")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class RaceTemplate {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

}
