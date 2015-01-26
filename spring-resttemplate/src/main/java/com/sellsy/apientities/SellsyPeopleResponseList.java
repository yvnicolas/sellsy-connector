/**
 * 
 */
package com.sellsy.apientities;

import java.util.Map;

/**
 * @author yves
 *
 */
/**
 * @author yves
 *
 */
public class SellsyPeopleResponseList {

    
    private SellsyResponseInfo infos;
    
    private Map<String,Object> result;
    /**
     * 
     */
    public SellsyPeopleResponseList() {
        // TODO Auto-generated constructor stub
    }
    public SellsyResponseInfo getInfos() {
        return infos;
    }
    public void setInfos(SellsyResponseInfo infos) {
        this.infos = infos;
    }
    public Map<String, Object> getResult() {
        return result;
    }
    public void setResult(Map<String, Object> result) {
        this.result = result;
    }

}
