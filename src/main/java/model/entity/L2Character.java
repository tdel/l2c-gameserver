package model.entity;

import model.entity.embeddable.CharacterAppearence;
import model.entity.template.CharacterTemplate;

import javax.persistence.*;

@Entity
@Table(name = "l2character")
public class L2Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="fk_account_id")
    private int accountId;

    @Column
    private String login;

    @ManyToOne
    @JoinColumn(name="fk_template_id", referencedColumnName="id")
    private CharacterTemplate template;

    @Embedded
    private CharacterAppearence appearence;


    public int getId() {
        return this.id;
    }

    public void setAccount(int _accountId) {
        this.accountId = _accountId;
    }
    public int getAccount() {
        return this.accountId;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String _login) {
        this.login = _login;
    }

    public CharacterTemplate getTemplate() {
        return this.template;
    }
    public void setTemplate(CharacterTemplate _template) {
        this.template = _template;
    }

    public CharacterAppearence getAppearence() {
        return this.appearence;
    }
    public void setAppearence(CharacterAppearence _appearence) {
        this.appearence = _appearence;
    }


}
