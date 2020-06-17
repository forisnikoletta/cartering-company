package edu.ubb.cartering.backend.enums;

public enum USER_ROLES {
    ADMIN(0), USER(1);

    private int role;
    USER_ROLES(int num) {
        this.role = num;
    }

    public int getRole() {
        return role;
    }
}
