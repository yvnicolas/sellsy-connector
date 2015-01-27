package com.sellsy.apiservices;

import java.util.Map;

import com.sellsy.apientities.SellsyAPIMethod;

public interface SellsyRequestExecutor {

    /** Submits an API request to Sellsy and returns the result as a raw string result that needs to be Json decoded.
     * @param method
     * @param params
     * @return
     */
    public abstract String stringSubmit(String method, Object params);
    
    // Following method are work in progress used mainly for tests
    
    public abstract Object typedSubmit (SellsyAPIMethod method, Object params) throws SellsyApiException;
    
    public abstract Map<String, Object> mapSubmit (String method, Map<String, Object> params) throws SellsyApiException;

}