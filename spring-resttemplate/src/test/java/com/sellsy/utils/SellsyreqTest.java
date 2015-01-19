package com.sellsy.utils;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SellsyreqTest {

    private static final Logger logger = LoggerFactory.getLogger(SellsyreqTest.class);

    // Keys
    private static String consumerToken="2b0ad3000cac95ca2a73b81b4adabe72d0b94e57";
    private static String consumerSecret="181a01c3eac529008d9507b968c3b5a80ee4f5e7";
    private static String userToken="816deb4e9946d421ed8b3b50230503ba0baa6cdf";
    private static String userSecret="00d75564839d21dee76fcc9b32948dd5012a3078";
    private static SellsySpringRestExecutor underTest = new SellsySpringRestExecutor(consumerToken, consumerSecret, userToken, userSecret);
    
    @Test
    public void test() {
        String result = underTest.submit("Peoples.getList", 
               "{pagination:{nberpage:10, pagenum:1},search:{contains:33184164473}");
        logger.info("Retour : {}", result);
    }
    
}
