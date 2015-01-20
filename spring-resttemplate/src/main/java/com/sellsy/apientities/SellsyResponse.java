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

    private Object response;
    private String error;
    private String status;
    
    public SellsyResponse() {
       
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
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
