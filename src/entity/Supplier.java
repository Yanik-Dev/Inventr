/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Vice Principal
 */
@Entity()
@Table(name="suppliers")
public class Supplier implements Serializable {
    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long Id;
    @Column(unique = true)
    private String name;
    @OneToOne(cascade=CascadeType.ALL, targetEntity = Address.class)
    private Address address; 
    @ManyToMany(targetEntity=Item.class)
    private List<Item> items;
    @OneToOne(cascade=CascadeType.ALL, targetEntity = ContactDetail.class)
    private ContactDetail contactDetail;
    /**
    /**
     * @return the Id
     */
    public long getId() {
        return Id;
    }

    /**
     * @param Id the Id to set
     */
    public void setId(long Id) {
        this.Id = Id;
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
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(Address address) {
        this.address = address;
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

    /**
     * @return the contactDetail
     */
    public ContactDetail getContactDetail() {
        return contactDetail;
    }

    /**
     * @param contactDetail the contactDetail to set
     */
    public void setContactDetail(ContactDetail contactDetail) {
        this.contactDetail = contactDetail;
    }




}
