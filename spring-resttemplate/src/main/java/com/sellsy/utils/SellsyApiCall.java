/**
 * 
 */
package com.sellsy.utils;

/**
 * @author yves
 *
 */
public class SellsyApiCall {

    private String method;
    private String params;

    public SellsyApiCall() {
        super();
    }

    public SellsyApiCall(String method, String params) {
        super();
        this.method = method;
        this.params = params;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

}
