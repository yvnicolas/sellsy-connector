/**
 * 
 */
package com.sellsy.coreConnector;

import java.util.Map;

/**
 * Encapsulates the raw Map response result from the Json Call
 * 
 * @author yves
 *
 */
public class SellsyApiResponse  {
    
    private Map<String, Object> attributeMap;

    /**
     * 
     */
    public SellsyApiResponse() {
        // TODO Auto-generated constructor stub
    }

    protected SellsyApiResponse(Map<String, Object> attributeMap) {
        this.attributeMap = attributeMap;
    }

    public Object getResponseAttribute(String attribute) {
        return attributeMap.get(attribute);
    }

    @Override
    public String toString() {
        return "SellsyApiResponse [attributeMap=" + attributeMap + "]";
    }
    
    
}
