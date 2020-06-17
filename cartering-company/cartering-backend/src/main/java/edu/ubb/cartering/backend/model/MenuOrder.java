package edu.ubb.cartering.backend.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "menuorders")
@NamedQueries({
        @NamedQuery(name = MenuOrder.QUERY_FIND_BY_MENU, query = "Select mo from MenuOrder mo where mo.menu.id = :menuId"),
        @NamedQuery(name = MenuOrder.QUERY_FIND_BY_USER, query = "Select mo from MenuOrder mo where mo.user.id = :userId")
})
public class MenuOrder extends BaseEntity {

    public static final String QUERY_FIND_BY_USER =  "MenuOrder.findByUserId";
    public static final String QUERY_FIND_BY_MENU =  "MenuOrder.findByMenuId";

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "menuId")
    private Menu menu;

    @Column(nullable = false)
    private Date date;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "menuOrder")
    private List<State> states;

    public MenuOrder() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<State> getStates() {
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }

    @Override
    public String toString() {
        return "MenuOrder{" +
                "user=" + user +
                ", menu=" + menu +
                ", date=" + date +
                ", states=" + states +
                '}';
    }

    public int getLastState() {
        if(states == null || states.isEmpty()) {
            return  -1;
        } else {
            return states.get(states.size() - 1).getState();
        }
    }
}
