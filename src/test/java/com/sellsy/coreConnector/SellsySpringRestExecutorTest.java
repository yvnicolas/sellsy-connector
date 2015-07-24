/**
 * 
 */
package com.sellsy.coreConnector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.List;

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
 * 
 * @author yves
 * 
 */
public class SellsySpringRestExecutorTest {

    private static final Logger logger = LoggerFactory.getLogger(SellsySpringRestExecutorTest.class);

    // Keys Make sure you change them to have valid keys
    private static String consumerToken = "2d4682106050873fe60fca4e2a182d8b889a67af";
    private static String consumerSecret = "27060a3a99df8087f10b52753a74bca4a6168f93";
    private static String userToken = "7f5b728c05460e91051ca4dcadc14c51983f8954";
    private static String userSecret = "64663c0482babc51dfe3b3fe0d4065fd9b3c040f";
  
    private static SellsySpringRestExecutor underTest = new SellsySpringRestExecutor(consumerToken, consumerSecret,
            userToken, userSecret);

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

        SellsyApiRequest request = new SellsyApiRequest("Infos.getInfos", new HashMap<String, Object>());
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
     * 
     * @throws SellsyApiException
     */
    @Test
    public void found1test() throws SellsyApiException {
        ListGetParams params = new ListGetParams();
        params.setPagination(new Pagination(10, 1));
        params.setSearch(new SearchFilter("49 03 21 05"));
        SellsyApiRequest request = new SellsyApiRequest("Peoples.getList", params);
        SellsyApiResponse result = underTest.process(request);

        SellsyResponseInfo infos = OBJECTMAPPER.convertValue(result.getResponseAttribute("infos"),
                SellsyResponseInfo.class);
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

        SellsyResponseInfo infos = OBJECTMAPPER.convertValue(result.getResponseAttribute("infos"),
                SellsyResponseInfo.class);
        logger.debug("Result info : {}", infos.toString());
        logger.debug("Real result : {}", result.getResponseAttribute("result").toString());
        assertEquals(0, infos.getNbtotal());
    }

    @Test
    public void testList() throws SellsyApiException {
        ListGetParams params = new ListGetParams();
        params.setPagination(new Pagination(10, 1));
        params.setSearch(new SearchFilter("4921 05"));
        SellsyApiRequest request = new SellsyApiRequest("Peoples.getList", params);
        SellsyApiResponse result = underTest.process(request);

        List<SellsyApiResponse> listContacts = SellsyApiResponseManip.extractResponseList(result);
        logger.debug(String.format("Found %s results", listContacts.size()));
        assertEquals(8, listContacts.size());
        int i = 0;
        for (SellsyApiResponse contact : listContacts) {
            i++;
            logger.debug(String.format("contact %s : %s %s", i, contact.getResponseAttribute("forename"),
                    contact.getResponseAttribute("name")));
        }
    }

    

    @Test
    public void testPoc() throws SellsyApiException {
        ListGetParams params = new ListGetParams();
        params.setPagination(new Pagination(10, 1));
        params.setSearch(new SearchFilter("0698017310"));
        SellsyApiRequest request = new SellsyApiRequest("Peoples.getList", params);
        SellsyApiResponse result = underTest.process(request);

        List<SellsyApiResponse> listContacts = SellsyApiResponseManip.extractResponseList(result);
        logger.debug(String.format("Found %s results", listContacts.size()));
        int i = 0;
        for (SellsyApiResponse contact : listContacts) {
            i++;
            logger.debug(String.format("contact %s : %s %s", i, contact.getResponseAttribute("forename"),
                    contact.getResponseAttribute("name")));
            logger.debug("{}", contact.toString());
            Object linked = contact.getResponseAttribute("linked");
            logger.debug("Linked Attribute, TYpe : {}, value : {}", linked.getClass(), linked.toString());
            Object linkedRelationtypes = contact.getResponseAttribute("linkedRelationTypes");
            logger.debug("linkedRelationtypes Attribute, TYpe : {}, value : {}", linkedRelationtypes.getClass(), linkedRelationtypes.toString());
            
        }
    }
}
