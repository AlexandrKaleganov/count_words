package ru.callinsicght.countwords.model;


import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

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
@Table(name = "tableList")
public class TableList extends AllModels {
    /**
     * наименование приложения
     */
    @Column(name = "name")
    private String name;
    /**
     * база данных к которой относится таблица
     */
    @Getter
    @Setter
    @NonNull
    @ManyToOne
    @JoinColumn(name = "data_base_id")
    private DataBase dataBase;
    /**
     * пользователь, который собрал статистику
     */
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * список приложений к которым относится данныя таблица
     */
    @Getter
    @Setter
    @ManyToMany
    @JoinTable(name = "app_table_list",
            joinColumns = @JoinColumn(name = "tableList_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "app_id", referencedColumnName = "id"))
    private Set<App> appList;
}
