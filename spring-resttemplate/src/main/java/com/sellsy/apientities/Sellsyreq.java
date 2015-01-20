package com.sellsy.apientities;


/**
 * Abstraction of a Sellsy api request pure test for the moment
 * 
 * @author Yves Nicolas
 * 
 */
public class Sellsyreq {

    private int request = 1;
    private String io_mode = "json";
    private SellsyApiCall do_in;

    public Sellsyreq() {
        super();

    }

    public Sellsyreq(SellsyApiCall call) {
        super();
        this.do_in = call;
    }

    public int getRequest() {
        return request;
    }

    public void setRequest(int request) {
        this.request = request;
    }

    public String getIo_mode() {
        return io_mode;
    }

    public void setIo_mode(String io_mode) {
        this.io_mode = io_mode;
    }

    public SellsyApiCall getDo_in() {
        return do_in;
    }

    public void setDo_in(SellsyApiCall do_in) {
        this.do_in = do_in;
    }

 
}
