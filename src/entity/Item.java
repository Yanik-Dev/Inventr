/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import constant.Status;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Vice Principal
 */

@Entity()
@Table(name="items")
public class Item implements Serializable {
    @Id()
    private String itemCode;
    private String itemName;
    private String description;
    private double quantity;
    private double unitCost;
    private double reorderLevel;
    private Status status;
    @ManyToOne(targetEntity = Unit.class)
    @JoinColumn(name="UNIT_ID",referencedColumnName="UNIT_ID")
    private Unit unit;
    @ManyToOne(targetEntity = Category.class)
    @JoinColumn(name="CATEGORY_ID",referencedColumnName="CATEGORY_ID")
    private Category category;
    @ManyToMany(targetEntity=Supplier.class)
    private List<Supplier> suppliers;
    @ManyToOne(targetEntity = Location.class)
    @JoinColumn(name="LOCATION_ID",referencedColumnName="LOCATION_ID")
    private Location location;
    @ManyToOne(targetEntity=Receivable.class)
    private Receivable receivable; 
    /**
     * @return the itemCode
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * @param itemCode the itemCode to set
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    /**
     * @return the itemName
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * @param itemName the itemName to set
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the quantity
     */
    public double getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the unitCost
     */
    public double getUnitCost() {
        return unitCost;
    }

    /**
     * @param unitCost the unitCost to set
     */
    public void setUnitCost(double unitCost) {
        this.unitCost = unitCost;
    }

    /**
     * @return the reorderLevel
     */
    public double getReorderLevel() {
        return reorderLevel;
    }

    /**
     * @param reorderLevel the reorderLevel to set
     */
    public void setReorderLevel(double reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    /**
     * @return the status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * @return the unit
     */
    public Unit getUnit() {
        return unit;
    }

    /**
     * @param unit the unit to set
     */
    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    /**
     * @return the category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * @return the itemSupplier
     */
    public List<Supplier> getItemSupplier() {
        return getSuppliers();
    }

    /**
     * @return the location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * @return the suppliers
     */
    public List<Supplier> getSuppliers() {
        return suppliers;
    }

    /**
     * @param suppliers the suppliers to set
     */
    public void setSuppliers(List<Supplier> suppliers) {
        this.suppliers = suppliers;
    }

    /**
     * @return the receivables
     */
    public Receivable getReceivables() {
        return receivable;
    }

    /**
     * @param receivables the receivables to set
     */
    public void setReceivables(Receivable receivable) {
        this.receivable = receivable;
    }
}
