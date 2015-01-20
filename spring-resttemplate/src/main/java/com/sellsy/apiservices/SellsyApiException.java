/**
 * 
 */
package com.sellsy.apiservices;

/**
 * @author Yves Nicolas
 *
 */
public class SellsyApiException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * 
     */
   

    /**
     * 
     */
    public SellsyApiException() {
       
    }

    /**
     * @param message
     */
    public SellsyApiException(String message) {
        super(message);
      
    }

    /**
     * @param cause
     */
    public SellsyApiException(Throwable cause) {
        super(cause);
        
    }

    /**
     * @param message
     * @param cause
     */
    public SellsyApiException(String message, Throwable cause) {
        super(message, cause);
      
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public SellsyApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
     
    }

}
