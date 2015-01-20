/**
 * 
 */
package com.sellsy.apientities;

/**
 * @author yves
 *
 */
public class SearchFilter {
    
    private String contains;
    private Long birthdate;

    /**
     * 
     */
    public SearchFilter() {
       this.contains="";
       this.birthdate=null;
       
    }

    public SearchFilter(String contains) {
        this.birthdate=null;
        this.contains = contains;
    }

    public SearchFilter(String contains, Long birthdate) {
        this.contains = contains;
        this.birthdate = birthdate;
    }

    public String getContains() {
        return contains;
    }

    public void setContains(String contains) {
        this.contains = contains;
    }

    public Long getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Long birthdate) {
        this.birthdate = birthdate;
    }

    
    
}
