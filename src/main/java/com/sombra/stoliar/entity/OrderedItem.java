package com.sombra.stoliar.entity;


import javax.persistence.*;


@Entity
public class OrderedItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private Integer itemId;
    private String name;
    private Double price;
    @Column(length = 1500)
    private String description;
    private String imageReference;
    private Integer amount;
    @ManyToOne(targetEntity = BuyOrder.class)
    private BuyOrder buyOrder;

    public OrderedItem() {
    }

    public OrderedItem(String name, Double price, String description, String imageReference, Integer amount, BuyOrder buyOrder) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageReference = imageReference;
        this.amount = amount;
        this.buyOrder = buyOrder;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageReference() {
        return imageReference;
    }

    public void setImageReference(String imageReference) {
        this.imageReference = imageReference;
    }

    public BuyOrder getBuyOrder() {
        return buyOrder;
    }

    public void setBuyOrder(BuyOrder buyOrder) {
        this.buyOrder = buyOrder;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }


}
