package edu.ubb.cartering.backend.model;

import edu.ubb.cartering.backend.enums.ORDER_STATES;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "states")
public class State extends BaseEntity {

    @Column(nullable = false)
    private int state;

    @Column(nullable = false)
    private Date date;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name="menuorderId", nullable=false)
    private MenuOrder menuOrder;

    public State() {
    }

    public MenuOrder getMenuOrder() {
        return menuOrder;
    }

    public void setMenuOrder(MenuOrder menuOrder) {
        this.menuOrder = menuOrder;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setState(ORDER_STATES state) {
        this.state = state.getState();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "State{" +
                ", state=" + state +
                ", date=" + date +
                ", menuOrder=" + menuOrder +
                ", id=" + id +
                '}';
    }
}
