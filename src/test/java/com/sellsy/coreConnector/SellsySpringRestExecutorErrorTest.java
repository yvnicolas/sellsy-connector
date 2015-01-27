/**
 * 
 */
package com.sellsy.coreConnector;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test class to check correct behaviour in case of wrong keys
 * @author yves
 *
 */
public class SellsySpringRestExecutorErrorTest {
    
    private static final Logger logger = LoggerFactory.getLogger(SellsySpringRestExecutorErrorTest.class);

    @Test
    public void testwrongCon()  {
        
        String consumerToken="2b0ad3000cac9ca2a73b81b4adabe72d0b94e57";
        String consumerSecret="181a01c3eac529008d9507b968c3b5a80ee4f5e7";
        String userToken="816deb4e9946d421ed8b3b50230503ba0baa6cdf";
        String userSecret="00d75564839d21dee76fcc9b32948dd5012a3078";
        
        try {
            testConnect(consumerToken, consumerSecret, userToken, userSecret);
            fail("Should Raise Exception");
        } catch (SellsyApiException e) {
            // check that call wath unauthorized
            assertTrue (e.getCause().getClass().equals(org.springframework.web.client.HttpClientErrorException.class));
            assertTrue (e.getMessage().contains("401"));
        }
    }

    
    @Test
    public void testwrongConsSecret()  {
        
        String consumerToken="2b0ad3000cac95ca2a73b81b4adabe72d0b94e57";
        String consumerSecret="181a01c3eacd9507b968c3b5a80ee4f5e7";
        String userToken="816deb4e9946d421ed8b3b50230503ba0baa6cdf";
        String userSecret="00d75564839d21dee76fcc9b32948dd5012a3078";
        
        try {
            testConnect(consumerToken, consumerSecret, userToken, userSecret);
            fail("Should Raise Exception");
        } catch (SellsyApiException e) {
            // check that call wath unauthorized
            assertTrue (e.getCause().getClass().equals(org.springframework.web.client.HttpClientErrorException.class));
            assertTrue (e.getMessage().contains("401"));
        }
    }
    
    
    @Test
    public void testwrongUserToken()  {
        
        String consumerToken="2b0ad3000cac95ca2a73b81b4adabe72d0b94e57";
        String consumerSecret="181a01c3eac529008d9507b968c3b5a80ee4f5e7";
        String userToken="deb4e9946d421ed8b3b50230503ba0baa6cdf";
        String userSecret="00d75564839d21dee76fcc9b32948dd5012a3078";
        
        try {
            testConnect(consumerToken, consumerSecret, userToken, userSecret);
            fail("Should Raise Exception");
        } catch (SellsyApiException e) {
            // check that call wath unauthorized
            assertTrue (e.getCause().getClass().equals(org.springframework.web.client.HttpClientErrorException.class));
            assertTrue (e.getMessage().contains("401"));
        }
    }
    
    
    @Test
    public void testwrongUserSecret()  {
        
        String consumerToken="2b0ad3000cac95ca2a73b81b4adabe72d0b94e57";
        String consumerSecret="181a01c3eac529008d9507b968c3b5a80ee4f5e7";
        String userToken="816deb4e9946d421ed8b3b50230503ba0baa6cdf";
        String userSecret="00d75564839d21dee76fcc9b32948dd5012a38";
        
        try {
            testConnect(consumerToken, consumerSecret, userToken, userSecret);
            fail("Should Raise Exception");
        } catch (SellsyApiException e) {
            // check that call wath unauthorized
            assertTrue (e.getCause().getClass().equals(org.springframework.web.client.HttpClientErrorException.class));
            assertTrue (e.getMessage().contains("401"));
        }
    }
    @Test
    public void testnullCon()  {
        
       
        String consumerSecret="181a01c3eac529008d9507b968c3b5a80ee4f5e7";
        String userToken="816deb4e9946d421ed8b3b50230503ba0baa6cdf";
        String userSecret="00d75564839d21dee76fcc9b32948dd5012a3078";
        
        try {
            testConnect(null, consumerSecret, userToken, userSecret);
            fail("Should Raise Exception");
        } catch (SellsyApiException e) {
            // check that call wath unauthorized
            assertTrue (e.getCause().getClass().equals(org.springframework.web.client.HttpClientErrorException.class));
            assertTrue (e.getMessage().contains("401"));
        }
    }
    
    private void testConnect(String consumerToken, String consumerSecret, String userToken, String userSecret) throws SellsyApiException {
        SellsySpringRestExecutor underTest = new SellsySpringRestExecutor(consumerToken, consumerSecret, userToken, userSecret);
        SellsyApiRequest request = new SellsyApiRequest("Infos.getInfos", new HashMap<String,Object>());
        SellsyApiResponse result = underTest.process(request);
        logger.info("Retour : {}", result.toString());
        assertNotNull(result);
    }
}
