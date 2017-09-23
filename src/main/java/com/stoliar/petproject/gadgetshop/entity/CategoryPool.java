package com.stoliar.petproject.gadgetshop.entity;

import javax.persistence.*;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = "findCategoryPoolByName", query = "select c from CategoryPool c where c.name= :name"),
        @NamedQuery(name = "findAllCategoryPools", query = "from CategoryPool")
})
@Entity
public class CategoryPool {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "categoryPool", fetch = FetchType.EAGER)
    private List<Category> categories;

    public CategoryPool() {
    }

    public CategoryPool(String name) {
        this.name = name;
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

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoryPool categoryPool = (CategoryPool) o;

        return name != null ? name.equals(categoryPool.name) : categoryPool.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
