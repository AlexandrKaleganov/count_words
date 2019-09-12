package ru.callinsicght.countwords.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.HashSet;
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
@Table(name = "apps")
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
    @ManyToOne
    @JoinColumn(name = "data_base_id", nullable = false)
    @Fetch(FetchMode.JOIN)
    private DataBase dataBase;

    public App(int id) {
        super(id);
    }

    public App() {
        super();
    }
//
//    /**
//     * список таблиц к в которых упоминается данное приложение
//     */
//    @Getter
//    @Setter
//    @ManyToMany
//    @JoinTable(name = "app_table_list",
//            joinColumns = @JoinColumn(name = "app_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "tableList_id", referencedColumnName = "id"))
//    private Set<TableList> tableList = new HashSet<>();

}
