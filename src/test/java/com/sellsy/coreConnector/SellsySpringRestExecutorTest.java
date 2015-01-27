/**
 * 
 */
package com.sellsy.coreConnector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sellsy.apientities.ListGetParams;
import com.sellsy.apientities.Pagination;
import com.sellsy.apientities.SearchFilter;
import com.sellsy.apientities.SellsyResponseInfo;

/**
 * 
 * Set of connection test to the Sellsy API Peoples.getList method.
 * @author yves
 *
 */
public class SellsySpringRestExecutorTest {

  private static final Logger logger = LoggerFactory.getLogger(SellsySpringRestExecutorTest.class);
    
    
    // Keys Make sure you change them to have valid keys
    private static String consumerToken="2b0ad3000cac95ca2a73b81b4adabe72d0b94e57";
    private static String consumerSecret="181a01c3eac529008d9507b968c3b5a80ee4f5e7";
    private static String userToken="816deb4e9946d421ed8b3b50230503ba0baa6cdf";
    private static String userSecret="00d75564839d21dee76fcc9b32948dd5012a3078";
    
    
    private static SellsySpringRestExecutor underTest = new SellsySpringRestExecutor(consumerToken, consumerSecret, userToken, userSecret);
    
    private static final ObjectMapper OBJECTMAPPER = new ObjectMapper();
    

    @Test
    public void test() throws SellsyApiException {
        ListGetParams params = new ListGetParams();
        params.setPagination(new Pagination(10, 1));
        params.setSearch(new SearchFilter("610278087"));
        SellsyApiRequest request = new SellsyApiRequest("Peoples.getList", params);
        SellsyApiResponse result = underTest.process(request);
        logger.info("Retour : {}", result.toString());
        assertNotNull(result);
    }

    

    @Test
    public void testinfos() throws SellsyApiException {
     
        SellsyApiRequest request = new SellsyApiRequest("Infos.getInfos", new HashMap<String,Object>());
        SellsyApiResponse result = underTest.process(request);
        logger.info("Retour : {}", result.toString());
        assertNotNull(result);
    }
    /**
     * Malformed api request (no param) check exception raising
     */
    @Test
    public void testError() {

        SellsyApiRequest request = new SellsyApiRequest("Peoples.getList", null);
        try {
            underTest.process(request);
            fail("should raise Exception");
        } catch (SellsyApiException e) {
            logger.debug("raised exception {}", e.toString());
            assertTrue(e.getMessage().contains("E_DO_IN_PARAM_MISSING"));
            assertTrue(e.getMessage().contains("Call to Peoples.getList"));
        }
    }
    /**
     * Should find one result to the Peoples.getList method
     * @throws SellsyApiException
     */
    @Test
    public void found1test() throws SellsyApiException {
        ListGetParams params = new ListGetParams();
        params.setPagination(new Pagination(10, 1));
        params.setSearch(new SearchFilter("49 03 21 05"));
        SellsyApiRequest request = new SellsyApiRequest("Peoples.getList", params);
        SellsyApiResponse result = underTest.process(request);
        
        SellsyResponseInfo infos = OBJECTMAPPER.convertValue(result.getResponseAttribute("infos"), SellsyResponseInfo.class);
        logger.debug("Result info : {}", infos.toString());
        logger.debug("Real result : {}", result.getResponseAttribute("result").toString());
        assertEquals(1, infos.getNbtotal());
    }
    
    
    // ce test ne marche pas pour l'instant, le result null génère une exception
    @Test
    public void found0test() throws SellsyApiException {
        ListGetParams params = new ListGetParams();
        params.setPagination(new Pagination(10, 1));
        params.setSearch(new SearchFilter("4921 05"));
        SellsyApiRequest request = new SellsyApiRequest("Peoples.getList", params);
        SellsyApiResponse result = underTest.process(request);
        
        SellsyResponseInfo infos = OBJECTMAPPER.convertValue(result.getResponseAttribute("infos"), SellsyResponseInfo.class);
        logger.debug("Result info : {}", infos.toString());
        logger.debug("Real result : {}", result.getResponseAttribute("result").toString());
        assertEquals(0, infos.getNbtotal());
    }
    

}
