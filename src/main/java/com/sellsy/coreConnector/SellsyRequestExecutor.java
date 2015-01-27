package com.sellsy.coreConnector;


/**
 * Sellsy Api Access Interface
 * @author yves
 *
 */
public interface SellsyRequestExecutor {

    /**
     * Generic call to Sellsy API
     * @param request
     * @return
     * @throws SellsyApiException with proper Error codes return by the API
     */
    public abstract SellsyApiResponse process(SellsyApiRequest request) throws SellsyApiException;

}