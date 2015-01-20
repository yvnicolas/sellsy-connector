/**
 * 
 */
package com.sellsy.apientities;

/**
 * @author yves
 *
 */
public class SellsyApiCall {

    private String method;
    private Object params;

    public SellsyApiCall() {
        super();
    }

    public SellsyApiCall(String method, Object params) {
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

    public Object getParams() {
        return params;
    }

    public void setParams(Object params) {
        this.params = params;
    }

}
