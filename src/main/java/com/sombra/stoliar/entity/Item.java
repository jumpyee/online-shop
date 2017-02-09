package com.sombra.stoliar.entity;


import javax.persistence.*;


@NamedQueries({
        @NamedQuery(name = "findItemsByCategoryAndGroup", query = "select i from Item i where i.category.name= :categoryName and i.category.categoryPool.name= :groupName" ),
        @NamedQuery(name = "findAllItems", query = "from Item")
})
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String name;
    private Double price;
    private String description;
    private String imageReference;
    @ManyToOne
    private Category category;



    public Item() {
    }

    public Item(String name, Double price, String description, Category category, String imageReference) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
        this.imageReference = imageReference;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getImageReference() {
        return imageReference;
    }

    public void setImageReference(String imageReference) {
        this.imageReference = imageReference;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", category=" + category +
                '}';
    }
}
