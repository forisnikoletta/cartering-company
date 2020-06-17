package edu.ubb.cartering.backend.model;

import edu.ubb.cartering.backend.enums.USER_ROLES;

import javax.persistence.*;

@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = User.QUERY_FIND_BY_EMAIL, query = "from User u where u.email= :email")
})
public class User extends BaseEntity {
    public static final String QUERY_FIND_BY_EMAIL =  "User.findByEmail";

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String first;

    @Column(nullable = false)
    private String last;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private int role;

    @Column(nullable = false)
    private String password;


    public User() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public void setRole(USER_ROLES role) {
        this.role = role.getRole();
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", first='" + first + '\'' +
                ", last='" + last + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", id=" + id +
                '}';
    }
}
