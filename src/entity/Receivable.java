/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Vice Principal
 */
@Entity
public class Receivable implements Serializable {
  
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @OneToMany(mappedBy="receivable",targetEntity=Item.class,fetch=FetchType.LAZY)
    private List<Item> item;
    @OneToMany(cascade=CascadeType.ALL,targetEntity = Supplier.class)
    private List<Supplier> supplier;
    private double amount;
    private String dateReceived;
    private double cost;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the item
     */
    public List<Item> getItem() {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(List<Item> item) {
        this.item = item;
    }

    /**
     * @return the supplier
     */
    public List<Supplier> getSupplier() {
        return supplier;
    }

    /**
     * @param supplier the supplier to set
     */
    public void setSupplier(List<Supplier> supplier) {
        this.supplier = supplier;
    }

    /**
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * @return the dateReceived
     */
    public String getDateReceived() {
        return dateReceived;
    }

    /**
     * @param dateReceived the dateReceived to set
     */
    public void setDateReceived(String dateReceived) {
        this.dateReceived = dateReceived;
    }

    /**
     * @return the cost
     */
    public double getCost() {
        return cost;
    }

    /**
     * @param cost the cost to set
     */
    public void setCost(double cost) {
        this.cost = cost;
    }


    
}
