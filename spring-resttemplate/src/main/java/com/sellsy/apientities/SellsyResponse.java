/**
 * 
 */
package com.sellsy.apientities;

/**
 * Sellsy High level response entity.
 * Example : {"response":null,"error":"","status":"success"}
 * @author Yves Nicolas
 *
 */
public class SellsyResponse {

    private String response;
    private String error;
    private String status;
    
    public SellsyResponse() {
       
    }
 
   
    public String getJsonResponse() {
        return response;
    }


    public void setJsonResponse(String jsonResponse) {
        this.response = jsonResponse;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
