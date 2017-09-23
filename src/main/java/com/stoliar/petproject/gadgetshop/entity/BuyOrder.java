package com.stoliar.petproject.gadgetshop.entity;

import javax.persistence.*;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = "findAllBuyOrders", query = " from BuyOrder "),
})


@Entity
public class BuyOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private Double totalPrice;
    private String date;
    private String status = "In queue for processing";
    @OneToMany(mappedBy = "buyOrder", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<OrderedItem> orderedItems;
    @ManyToOne(targetEntity = User.class)
    private User user;


    public BuyOrder() {
    }

    public BuyOrder(Double totalPrice, String date, Set<OrderedItem> orderedItems, User user) {
        this.totalPrice = totalPrice;
        this.date = date;
        this.orderedItems = orderedItems;
        this.user = user;
        for (OrderedItem orderedItem : orderedItems) {
            orderedItem.setBuyOrder(this);
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<OrderedItem> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(Set<OrderedItem> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
