/**
 * 
 */
package com.sellsy.objectentities;

import com.sellsy.coreConnector.SellsyApiResponse;

/**
 * Top level class for Sellsy database entities
 * @author Yves Nicolas
 *
 */
public class SellsyObject {

    private String id;
    private String ownerid;
    
    // the full Sellsy map object representation as returned by Selssy API
    private SellsyApiResponse apiResponse;
    

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

    

    public SellsyApiResponse getApiResponse() {
        return apiResponse;
    }


    public void setApiResponse(SellsyApiResponse apiResponse) {
        this.apiResponse = apiResponse;
    }


    @Override
    public String toString() {
        return "SellsyObject [id=" + id + ", ownerid=" + ownerid + "]";
    }
    
}
