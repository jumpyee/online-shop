package com.sombra.stoliar.entity;


import com.sombra.stoliar.model.UserRegistrationForm;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@NamedQueries({
        @NamedQuery(name = "delete", query = "delete  from User u where u.name= :name and u.surname= :surname and u.password = :password and u.email = :email and u.phoneNumber= :phonenumber"),
        @NamedQuery(name = "findAllUsers", query = " from User "),
        @NamedQuery(name = "findUserByEmail", query = "select u from User u where u.email= :email"),
})
@Entity
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String name;
    private String surname;
    private String password;
    @Column(unique = true)
    private String email;
    private String phoneNumber;
    private boolean isBanned = false;
    private String role;


    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_cart",joinColumns = @JoinColumn(name = "user_id"))
    @MapKeyJoinColumn(name = "item_id")
    @Column(name = "item_amount")
    private Map<Item, Integer> cart = new HashMap<>();



    public User() {
    }

    public User(UserRegistrationForm userForm) {
        this.name = userForm.getName();
        this.surname = userForm.getSurname();
        this.password = userForm.getPassword();
        this.email = userForm.getEmail();
        this.phoneNumber = userForm.getPhoneNumber();
        this.role = "user";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Map<Item, Integer> getCart() {
        return cart;
    }

    public void setCart(Map<Item, Integer> cart) {
        this.cart = cart;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isBanned() {
        return isBanned;
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
    }


}
