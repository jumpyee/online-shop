package com.sombra.stoliar.entity;


import javax.persistence.*;
import java.util.List;
import java.util.Map;
import java.util.Set;


@NamedQueries({
        @NamedQuery(name = "findItemsByCategoryAndGroup", query = "select i from Item i where i.category.name= :categoryName and i.category.categoryPool.name= :groupName"),
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

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_cart", joinColumns = @JoinColumn(name = "item_id"))
    @MapKeyJoinColumn(name = "user_id")
    @Column(name = "item_amount")
    private Map<User,Integer> userCarts;

    @PreRemove
    public void preDelete() {
        for (User user : userCarts.keySet()) {
            user.getCart().remove(this);
        }
    }

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

    public Map<User, Integer> getUserCarts() {
        return userCarts;
    }

    public void setUserCarts(Map<User, Integer> userCarts) {
        this.userCarts = userCarts;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (name != null ? !name.equals(item.name) : item.name != null) return false;
        if (price != null ? !price.equals(item.price) : item.price != null) return false;
        if (description != null ? !description.equals(item.description) : item.description != null) return false;
        if (imageReference != null ? !imageReference.equals(item.imageReference) : item.imageReference != null)
            return false;
        return category != null ? category.equals(item.category) : item.category == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (imageReference != null ? imageReference.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        return result;
    }
}
