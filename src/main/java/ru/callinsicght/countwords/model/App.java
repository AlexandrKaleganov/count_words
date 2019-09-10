package ru.callinsicght.countwords.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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
@Table(name = "roles")
public class App extends AllModels {
    /**
     * id приложения в бд app_statistic на сервере из таблицы app
     */
    @Column(name = "id_app")
    private Long idApp;
    /**
     * наименование приложения
     */
    @Column(name = "name")
    private String name;
    /**
     * номер телефона приложения
     */
    @Column(name = "exten")
    private int exten;
    /**
     * порт приложения
     */
    @Column(name = "port")
    private int port;
    @Column(name = "project_name")
    private String projectName;
    @Column(name = "dataBase_id")
    private DataBase dataBase;

    public App(int id) {
        super(id);
    }

    public App() {
        super();
    }

    @Override
    public String toString() {
        return "App{" + "idApp=" + idApp + ", name='" + name + '\'' + ", exten=" + exten
                + ", port=" + port + ", projectName='" + projectName + '\''
                + ", dataBase=" + dataBase + '}';
    }
}
