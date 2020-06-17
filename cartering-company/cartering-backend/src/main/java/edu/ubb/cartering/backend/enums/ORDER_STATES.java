package edu.ubb.cartering.backend.enums;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public enum ORDER_STATES {
    ACCEPTED(0, "Accepted"), PREPARATION(1, "Preparation"), PACKING(2, "Packing"), SHIPPING(3, "Shipping"), COMPLETED(4, "Completed");

    public static ORDER_STATES getState(int state) {
        List<ORDER_STATES> states = new ArrayList<ORDER_STATES>(EnumSet.allOf(ORDER_STATES.class));
        for(ORDER_STATES st : states) {
            if(st.getState() == state) {
                return st;
            }
        }
        return null;
    }
    private int state;
    private String name;
    ORDER_STATES(int state, String name) {
        this.state = state;
        this.name = name;
    }

    public int getState() {
        return state;
    }

    public String toString() { return name; }
}
