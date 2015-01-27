/**
 * 
 */
package com.sellsy.apientities;

/**
 * @author yves
 *
 */
public class SellsyResponseInfo  {

    private int nbpages;
    private int nbtotal;
    private int nbperpage=10;
    private int pagenum=1;
    
    /**
     * 
     */
    public SellsyResponseInfo() {
       
    }

 

    public int getNbtotal() {
        return nbtotal;
    }

    public void setNbtotal(int nbtotal) {
        this.nbtotal = nbtotal;
    }



    public int getNbpages() {
        return nbpages;
    }



    public void setNbpages(int nbpages) {
        this.nbpages = nbpages;
    }


  


    public int getNbperpage() {
        return nbperpage;
    }



    public void setNbperpage(int nbperpage) {
        this.nbperpage = nbperpage;
    }



    public int getPagenum() {
        return pagenum;
    }



    public void setPagenum(int pagenum) {
        this.pagenum = pagenum;
    }



    @Override
    public String toString() {
        return "SellsyResponseInfo [nbpages=" + nbpages + ", nbtotal=" + nbtotal + ", nberpage=" + nbperpage
                + ", pagenum=" + pagenum + "]";
    }




 

    
    
}
