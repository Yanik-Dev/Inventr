/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Vice Principal
 */
@Entity()
@Table(name="countries")
public class Country implements Serializable {
    @Id()
    @Column(name="COUNTRY_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long countryId;
    @Column(name="code")
    private String code;
    private String name;

    /**
     * @return the id
     */
    public long getCountryId() {
        return countryId;
    }

    /**
     * @param id the id to set
     */
    public void setCountryId(long id) {
        this.countryId = id;
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
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }
}
