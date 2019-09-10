package ru.callinsicght.countwords.model;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

/**
 * @author Kaleganov Aleander
 * @see DataBase#name
 * @see DataBase#ipBd
 * @see DataBase#password
 * @see DataBase#user
 * @since 06/05//2019
 * <br/>
 * <b>содержит поля:<b/>
 **/

@Entity
@Table(name = "data_base")
public class DataBase extends AllModels {
    /**
     * имя бд
     */
    @Column(name = "name")
    private String name;
    /**
     * ip бд
     */
    @Column(name = "ip_bd")
    private String ipBd;
    /**
     * пароль от бд
     */
    @Column(name = "password")
    private String password;
    /**
     * пользоаватель, который добавил бд в приложение
     */
    @Column(name = "user_id")
    private User user;
    /**
     * список приложени, которые есть в бд
     */
    @OneToMany(mappedBy = "app", cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.JOIN)
    private List<App> appList;

    public DataBase(int id) {
        super(id);
    }

    public DataBase() {
        super();
    }

    @Override
    public String toString() {
        return "DataBase{" + "name='" + name + '\'' + ", ipBd='" + ipBd + '\''
                + ", password='" + password + '\'' + ", user=" + user
                + ", appList=" + appList + '}';
    }
}