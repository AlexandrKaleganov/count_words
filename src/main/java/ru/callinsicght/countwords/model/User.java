package ru.callinsicght.countwords.model;


import javax.persistence.*;

/**
 * @author Kaleganov Aleander
 * @since 06/05//2019
 * <br/>
 * <b>содержит поля:<b/>
 */
@Entity
@Table(name = "user")
public class User extends AllModels {
    @Column(name = "name")
    private String name;
    @Column(name = "login")
    private String login;
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Roles roles;
    @Column(name = "password")
    private String password;

    public User(int id) {
        super(id);
    }

    public User() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + super.getId() + ", name='" + name + '\'' + ", login='"
                + login + '\'' + ", roles=" + roles + ", password='" + password + '\'' + '}';
    }
}
