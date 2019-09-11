package ru.callinsicght.countwords.model;


import javax.persistence.*;
import java.util.Set;

/**
 * список таблиц, которые были собраны по данной бд
 * @author Kaleganov Aleander
 * @since 06/05//2019
 * <br/>
 * <b>содержит поля:<b/>
 * @see TableList#name
 **/

@Entity
@Table(name = "roles")
public class TableList extends AllModels {
    /**
     * наименование приложения
     */
    @Column(name = "name")
    private String name;
    /**
     * база данных к которой относится таблица
     */
    @ManyToOne
    @JoinColumn(name = "data_base_id", nullable = false)
    private DataBase dataBase;
    /**
     * пользователь, который собрал статистику
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    /**
     * список приложений к которым относится данныя таблица
     */
    @ManyToMany
    @JoinTable(name = "app_table_list",
            joinColumns = @JoinColumn(name = "tableList_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "app_id", referencedColumnName = "id"))
    private Set<App> appList;
}
