package com.sellsy.apiservices;

import com.sellsy.apientities.SellsyAPIMethod;

public interface SellsyRequestExecutor {

    /** Submits an API request to Sellsy and returns the result as a raw string result that needs to be Json decoded.
     * @param method
     * @param params
     * @return
     */
    public abstract String submit(String method, Object params);
    
    public abstract Object submit (SellsyAPIMethod method, Object params) throws SellsyApiException;

}