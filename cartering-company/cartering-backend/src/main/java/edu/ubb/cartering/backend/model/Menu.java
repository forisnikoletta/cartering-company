package edu.ubb.cartering.backend.model;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "menus")
public class Menu extends BaseEntity {

    @Column(nullable = false)
    private String name;



    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "menufood",
            joinColumns = @JoinColumn(name = "menuid"),
            inverseJoinColumns = @JoinColumn(name = "foodid")
    )
    private Set<Food> foods;

    public Menu() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Food> getFoods() {
        return foods;
    }

    public void setFoods(Set<Food> foods) {
        this.foods = foods;
    }

    @Override
    public String toString() {
        return "Menu{" +
                ", foods=" + foods +
                '}';
    }
}
