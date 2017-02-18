package com.sombra.stoliar.entity;


import com.sombra.stoliar.model.UserRegistrationForm;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
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
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<BuyOrder> orders;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_cart", joinColumns = @JoinColumn(name = "user_id"))
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

    public List<BuyOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<BuyOrder> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (isBanned != user.isBanned) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (surname != null ? !surname.equals(user.surname) : user.surname != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(user.phoneNumber) : user.phoneNumber != null) return false;
        return role != null ? role.equals(user.role) : user.role == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (isBanned ? 1 : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }
}
