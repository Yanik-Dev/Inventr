/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Vice Principal
 */
@Entity()
@Table(name="categories")
public class Category implements Serializable {
   @Id()
   @Column(name = "CATEGORY_ID", nullable = false)
   @GeneratedValue(strategy = GenerationType.SEQUENCE)
   private long categoryId;
   private String name;
   @OneToMany(mappedBy="category",targetEntity=Item.class,fetch=FetchType.LAZY)
   private List<Item> items; 
   public Category(){}
    /**
     * @return the id
     */
    public long getCategoryId() {
        return categoryId;
    }

    /**
     * @param id the id to set
     */
    public void setCategoryId(long id) {
        this.categoryId = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the items
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(List<Item> items) {
        this.items = items;
    }
}
