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
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Vice Principal
 */
@Entity()
@Table(name="contact_details")
public class ContactDetail implements Serializable {
    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @OneToMany(cascade=CascadeType.ALL,targetEntity = PhoneNumber.class, fetch=FetchType.EAGER)
    private List<PhoneNumber> phoneNumbers;
    @OneToMany(cascade=CascadeType.ALL,targetEntity = Email.class,  fetch=FetchType.EAGER)
    private List<Email> emails;
    private String website;

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the website
     */
    public String getWebsite() {
        return website;
    }

    /**
     * @param website the website to set
     */
    public void setWebsite(String website) {
        this.website = website;
    }


    /**
     * @return the phoneNumbers
     */
    public List<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    /**
     * @param phoneNumbers the phoneNumbers to set
     */
    public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    /**
     * @return the emails
     */
    public List<Email> getEmails() {
        return emails;
    }

    /**
     * @param emails the emails to set
     */
    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }
    
}
