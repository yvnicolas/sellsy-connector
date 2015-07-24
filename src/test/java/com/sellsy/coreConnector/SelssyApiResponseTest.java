package com.sellsy.coreConnector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sellsy.apientities.SellsyResponseInfo;

public class SelssyApiResponseTest {
    private static final Logger logger = LoggerFactory.getLogger(SelssyApiResponseTest.class);
    
    

    @Test
    public void testRequeteUnique() throws IOException, SellsyApiException {
       String JsonString = responseFromFile("peopleGetOneAnswer.json");
       SellsyApiResponse apiResponse = new SellsyApiResponse(JsonString);
       logger.debug("read response : {}", apiResponse.toString());
       Set<String> firstLevelAttr = apiResponse.firstLevelAttributesList();
       logger.debug("Attributes list (one level) : {}", firstLevelAttr);
       assertEquals(37, firstLevelAttr.size());
       Set<String> allLevelAttr = apiResponse.allLevelAttributesList();
       logger.debug("Attributes liste (all level) : {}", allLevelAttr);
       assertEquals(47, allLevelAttr.size());
       Object name = apiResponse.getResponseAttribute("name");
       Object companyName=apiResponse.getResponseAttribute("linkeds.3222818.name");
       assertEquals("VAQUEZ", name);
       assertEquals("Avenir Conseil", companyName);
       
       
     
       
    }
    
    @Test
    public void testListAnswer() throws IOException, SellsyApiException {
        String JsonString = responseFromFile("peopleListAnswer.json");
        SellsyApiResponse apiResponse = new SellsyApiResponse(JsonString);
        logger.debug("read response : {}", apiResponse.toString());
        SellsyResponseInfo infos = apiResponse.getInfos();
        logger.debug("Infos : {}", infos.toString());
        assertEquals(10, infos.getNbpages());
        List<SellsyApiResponse> responseList = apiResponse.extractResponseList();
        assertEquals(1, responseList.size());
        
    }
    
    @Test
    public void testWrongList() throws IOException, SellsyApiException {
        String JsonString = responseFromFile("peopleGetOneAnswer.json");
        SellsyApiResponse apiResponse = new SellsyApiResponse(JsonString);
        
        try {
            apiResponse.extractResponseList();
            fail("Sould raise exception");
        } catch (SellsyApiException e) {
          logger.debug("{}", e.toString());
        }
    }

    
    private String responseFromFile(String path) throws IOException {
        InputStream is =
                this.getClass().getClassLoader().getResourceAsStream(path);
        return IOUtils.toString(is);
    }
}
