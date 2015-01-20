package com.sellsy.apiservices;

public interface SellsyRequestExecutor {

    /** Submits an API request to Sellsy and returns the result as a raw string result that needs to be Json decoded.
     * @param method
     * @param params
     * @return
     */
    public abstract String submit(String method, Object params);

}