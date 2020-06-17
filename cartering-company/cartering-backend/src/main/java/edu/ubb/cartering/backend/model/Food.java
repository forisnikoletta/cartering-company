package edu.ubb.cartering.backend.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "foods")
@NamedQueries({
        @NamedQuery(name = Food.QUERY_FIND_BY_NAME, query = "from Food f where f.name = :name")
})
public class Food extends BaseEntity {
    public static final String QUERY_FIND_BY_NAME=  "User.findByName";

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private int time;

    @ManyToMany(mappedBy = "foods")
    private List<Menu> menus;

    public Food() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menu) {
        this.menus = menus;
    }

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", time=" + time +
                '}';
    }
}
