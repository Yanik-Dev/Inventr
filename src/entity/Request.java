/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Vice Principal
 */
@Entity
@Table(name="requests")
public class Request implements Serializable {
        
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private double amount;
    @OneToMany(targetEntity = Item.class)
    private List<Item> item;
    @ManyToOne(targetEntity=User.class)
    private User requester;
    @ManyToOne(targetEntity=User.class)
    private User verifier;
    @ManyToOne(targetEntity=User.class)
    private User issuer;
    private String dateRequested;
    private String dateIssued;
    private String dateVerified;
    
    /**
     * @return the requester
     */
    public User getRequester() {
        return requester;
    }

    /**
     * @param requester the requester to set
     */
    public void setRequester(User requester) {
        this.requester = requester;
    }

    /**
     * @return the verifier
     */
    public User getVerifier() {
        return verifier;
    }

    /**
     * @param verifier the verifier to set
     */
    public void setVerifier(User verifier) {
        this.verifier = verifier;
    }

    /**
     * @return the issuer
     */
    public User getIssuer() {
        return issuer;
    }

    /**
     * @param issuer the issuer to set
     */
    public void setIssuer(User issuer) {
        this.issuer = issuer;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
     * @return the dateRequested
     */
    public String getDateRequested() {
        return dateRequested;
    }

    /**
     * @param dateRequested the dateRequested to set
     */
    public void setDateRequested(String dateRequested) {
        this.dateRequested = dateRequested;
    }

    /**
     * @return the dateIssued
     */
    public String getDateIssued() {
        return dateIssued;
    }

    /**
     * @param dateIssued the dateIssued to set
     */
    public void setDateIssued(String dateIssued) {
        this.dateIssued = dateIssued;
    }

    /**
     * @return the dateVerified
     */
    public String getDateVerified() {
        return dateVerified;
    }

    /**
     * @param dateVerified the dateVerified to set
     */
    public void setDateVerified(String dateVerified) {
        this.dateVerified = dateVerified;
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

   
}
