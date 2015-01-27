package com.sellsy.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sellsy.apientities.ListGetParams;
import com.sellsy.apientities.Pagination;
import com.sellsy.apientities.SearchFilter;
import com.sellsy.apientities.SellsyAPIMethod;
import com.sellsy.apientities.SellsyPeopleResponseList;
import com.sellsy.apiservices.SellsyApiException;
import com.sellsy.apiservices.SellsySpringRestExecutor;

public class SellsyreqTest {

    private static final Logger logger = LoggerFactory.getLogger(SellsyreqTest.class);
    
    
    // Keys
    private static String consumerToken="2b0ad3000cac95ca2a73b81b4adabe72d0b94e57";
    private static String consumerSecret="181a01c3eac529008d9507b968c3b5a80ee4f5e7";
    private static String userToken="816deb4e9946d421ed8b3b50230503ba0baa6cdf";
    private static String userSecret="00d75564839d21dee76fcc9b32948dd5012a3078";
    private static SellsySpringRestExecutor underTest = new SellsySpringRestExecutor(consumerToken, consumerSecret, userToken, userSecret);
    
    @Test
    public void test() throws JsonProcessingException {    
        ListGetParams params = new ListGetParams();
        params.setPagination(new Pagination(10, 1));
        params.setSearch(new SearchFilter("610278087"));
        String result = underTest.stringSubmit("Peoples.getList", params);
        logger.info("Retour : {}", result);
    }
    
    /**
     * Malformed api request (no param) check exception raising
     */
    @Test
    public void testError() {
  
    SellsyAPIMethod method = new SellsyAPIMethod("Peoples.getList", String.class);
    try {
        underTest.typedSubmit(method, null);
        fail ("should raise Exception");
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
        SellsyAPIMethod method = new SellsyAPIMethod("Peoples.getList", SellsyPeopleResponseList.class);
        ListGetParams params = new ListGetParams();
        params.setPagination(new Pagination(10, 1));
        params.setSearch(new SearchFilter("49 03 21 05"));
        SellsyPeopleResponseList result = (SellsyPeopleResponseList) underTest.typedSubmit(method, params);
        logger.debug("Result info : {}", result.getInfos().toString());
        logger.debug("Real result : {}", result.getResult().toString());
        assertEquals(1, result.getInfos().getNbtotal());
    }
    
    
    // ce test ne marche pas pour l'instant, le result null génère une exception
    @Test
    public void found0test() throws SellsyApiException {
        SellsyAPIMethod method = new SellsyAPIMethod("Peoples.getList", SellsyPeopleResponseList.class);
        ListGetParams params = new ListGetParams();
        params.setPagination(new Pagination(10, 1));
        params.setSearch(new SearchFilter("490321"));
        SellsyPeopleResponseList result = (SellsyPeopleResponseList) underTest.typedSubmit(method, params);
        logger.debug("Result info : {}", result.getInfos().toString());
        logger.debug("Real result : {}", result.getResult().toString());
        assertEquals(0, result.getInfos().getNbtotal());
    }
    
}
