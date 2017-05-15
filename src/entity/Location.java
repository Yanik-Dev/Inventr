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
 * @author VStudent
 */
@Entity()
@Table(name="locations")
public class Location implements Serializable {

    
    @Id
    @Column(name = "LOCATION_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long locationId;
    @Column(unique = true)
    private String name;
    
    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long id) {
        this.locationId = id;
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
    
}
