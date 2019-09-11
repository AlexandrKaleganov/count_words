package ru.callinsicght.countwords.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Kaleganov Aleander
 * @since 06/05//2019
 * <br/>
 * <b>содержит поля:<b/>
 * @see App#name
 * @see App#exten
 * @see App#idApp
 * @see App#port
 * @see App#dataBase
 * @see App#projectName
 **/

@Entity
@Table(name = "app")
public class App extends AllModels {
    /**
     * id приложения в бд app_statistic на сервере из таблицы app
     */
    @Getter
    @Setter
    @Column(name = "id_app")
    private Long idApp;
    /**
     * наименование приложения
     */
    @Getter
    @Setter
    @Column(name = "name")
    private String name;
    /**
     * номер телефона приложения
     */
    @Getter
    @Setter
    @Column(name = "exten")
    private int exten;
    /**
     * порт приложения
     */
    @Getter
    @Setter
    @Column(name = "port")
    private int port;
    /**
     * наименование проекта
     */
    @Getter
    @Setter
    @Column(name = "project_name")
    private String projectName;
    @Getter
    @Setter
    @Column(name = "dataBase_id")
    private DataBase dataBase;

    public App(int id) {
        super(id);
    }

    public App() {
        super();
    }

    /**
     * список таблиц к в которых упоминается данное приложение
     */
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "app_table_list",
            joinColumns = @JoinColumn(name = "app_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tableList_id", referencedColumnName = "id"))
    private Set<TableList> tableList;
    @Override
    public String toString() {
        return "App{" + "idApp=" + idApp + ", name='" + name + '\'' + ", exten=" + exten
                + ", port=" + port + ", projectName='" + projectName + '\''
                + ", dataBase=" + dataBase + '}';
    }
}
