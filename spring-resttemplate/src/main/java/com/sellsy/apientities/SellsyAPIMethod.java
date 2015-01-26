/**
 * 
 */
package com.sellsy.apientities;

/**
 * @author Yves Nicolas
 *
 */
public class SellsyAPIMethod {

    /**
     * 
     */
    public SellsyAPIMethod() {
        // TODO Auto-generated constructor stub
    }
    
    private String name;
    private Class<? extends Object> responseClass;
    
    
    public SellsyAPIMethod(String name, Class<? extends Object> responseClass) {
        this.name = name;
        this.responseClass = responseClass;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public Class<? extends Object> getResponseClass() {
        return responseClass;
    }


    public void setResponseClass(Class<? extends Object> responseClass) {
        this.responseClass = responseClass;
    }
    
    
    
    

}
