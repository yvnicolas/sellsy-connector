/**
 * 
 */
package com.sellsy.objectentities;

import com.sellsy.coreConnector.SellsyAPIConstants;
import com.sellsy.coreConnector.SellsyApiResponse;

/**
 * Top level class for Sellsy database entities
 * @author Yves Nicolas
 *
 */
public class SellsyObject {

    private String id;
    private String ownerid;
    
    // the full Sellsy Json tree object representation as returned by Selssy API
    private SellsyApiResponse apiResponse;
    

    public SellsyObject() {
    }
    
  
  
    public SellsyObject(SellsyApiResponse apiResponse) {
        this.apiResponse = apiResponse;
        this.id=apiResponse.getResponseAttribute(SellsyAPIConstants.ID);
        this.ownerid=apiResponse.getResponseAttribute(SellsyAPIConstants.OWNERID);
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

    
    
    /**
     * Enables to get value for whole inside attribute inside the tree of the response.
     * Syntax of attribute should use dot "." to go todeeperlevel. Example ("avatar.type")
     * @param attribute
     * @return
     */
    public String getAttributeValue(String attribute) {
    return this.apiResponse.getResponseAttribute(attribute);
    }

    @Override
    public String toString() {
        return "SellsyObject [id=" + id + ", ownerid=" + ownerid + ", fullTree =" + apiResponse + "]";
    }
    
}
