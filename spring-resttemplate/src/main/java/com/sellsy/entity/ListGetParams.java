/**
 * 
 */
package com.sellsy.entity;

/**
 * Parameters for Selssy List Requests
 * @author yves
 *
 */
public class ListGetParams {

    private Pagination pagination=null;
    private SearchFilter search=null;
   
    public ListGetParams() {
    }
   
    public Pagination getPagination() {
        return pagination;
    }
    
    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
    
    public SearchFilter getSearch() {
        return search;
    }
    
    public void setSearch(SearchFilter search) {
        this.search = search;
    }
    
    

}
