/**
 * 
 */
package com.sellsy.entity;

/**
 * @author yves
 *
 */
public class Pagination {
    
    private int nberpage=10;
    private int pagenum=1;
    
    
    public Pagination(int nberpage, int pagenum) {
        this.nberpage = nberpage;
        this.pagenum = pagenum;
    }


    public int getNberpage() {
        return nberpage;
    }


    public void setNberpage(int nberpage) {
        this.nberpage = nberpage;
    }


    public int getPagenum() {
        return pagenum;
    }


    public void setPagenum(int pagenum) {
        this.pagenum = pagenum;
    }
    
    

}
