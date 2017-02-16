package com.sombra.stoliar.entity;

import javax.persistence.*;
import java.io.Serializable;

@NamedQueries({
        @NamedQuery(name = "findCategoryByName", query = "select c from Category c where c.name= :name"),
        @NamedQuery(name = "findAllCategories", query = "from Category")
})
@Entity
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String name;

    @ManyToOne(targetEntity = CategoryPool.class)
    private CategoryPool categoryPool;


    public Category() {
    }

    public Category(String name, CategoryPool categoryPool) {
        this.name = name;
        this.categoryPool = categoryPool;
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

    public CategoryPool getCategoryPool() {
        return categoryPool;
    }

    public void setCategoryPool(CategoryPool categoryPool) {
        this.categoryPool = categoryPool;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (name != null ? !name.equals(category.name) : category.name != null) return false;
        return categoryPool != null ? categoryPool.equals(category.categoryPool) : category.categoryPool == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (categoryPool != null ? categoryPool.hashCode() : 0);
        return result;
    }
}
