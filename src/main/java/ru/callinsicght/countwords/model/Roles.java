package ru.callinsicght.countwords.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Kaleganov Aleander
 * @since 06/05//2019
 * <br/>
 * <b>содержит поля:<b/>
 **/

@Entity
@Table(name = "roles")
public class Roles extends AllModels {
    @Getter
    @Setter
    @Column(name = "name")
    private String name;

    public Roles(int id) {
        super(id);
    }

    public Roles() {
        super();
    }


    @Override
    public String toString() {
        return "Roles{" + "id=" + super.getId() + ", name='" + name + '\'' + '}';
    }
}
