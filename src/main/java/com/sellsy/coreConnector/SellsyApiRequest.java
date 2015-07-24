/**
 * 
 */
package com.sellsy.coreConnector;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * 
 * Low Level class to access Selssy Api
 * @author yves
 *
 */
public class SellsyApiRequest {

    private String method;
    private Object params;

    public SellsyApiRequest() {
        super();
    }

   

    public SellsyApiRequest(String method, Object params) {
        this.method = method;
        this.params = params;
    }


    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }



    public Object getParams() {
        return params;
    }



    public void Object (Map<String, Object> params) {
        this.params = params;
    }



    @Override
    public String toString() {
        return "SellsyApiRequest [method=" + method + ", params=" + params + "]";
    }



    public String json() throws SellsyApiException {
        try {
            return SellsyAPIConstants.OBJECTMAPPER.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new SellsyApiException(String.format("Unable to process request %s:%s",this.toString(), e.toString()), e);
        }
    }

  

}
