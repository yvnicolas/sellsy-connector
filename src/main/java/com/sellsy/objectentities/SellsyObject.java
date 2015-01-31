/**
 * 
 */
package com.sellsy.objectentities;

/**
 * Top level class for Sellsy database entities
 * @author Yves Nicolas
 *
 */
public class SellsyObject {

    private String id;
    private String ownerid;
    

    public SellsyObject() {
    }

  
    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public String getOwnerid() {
        return ownerid;
    }


    public void setOwnerid(String ownerid) {
        this.ownerid = ownerid;
    }


    @Override
    public String toString() {
        return "SellsyObject [id=" + id + ", ownerid=" + ownerid + "]";
    }
    
}
