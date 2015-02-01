package com.sellsy.coreConnector;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sellsy.objectentities.SellsyObject;
import com.sellsy.objectentities.SellsyPeople;

public class SellsyApiResponseManipTest {
    
    private static final Logger logger = LoggerFactory.getLogger(SellsyApiResponseManipTest.class);
    
    private static final ObjectMapper OBJECTMAPPER = new ObjectMapper();

    @Test
    public void testExtractResponseList() throws JsonParseException, JsonMappingException, IOException, SellsyApiException {
        SellsyApiResponse inputRepsonse = responseFromFile("peopleListAnswer.json");
        List<SellsyApiResponse> responseList = SellsyApiResponseManip.extractResponseList(inputRepsonse);
        assertEquals(1, responseList.size());
    }
    
    @Test
    public void testExtractVoidResponseList() throws JsonParseException, JsonMappingException, IOException, SellsyApiException {
        SellsyApiResponse inputRepsonse = responseFromFile("peopleListVoid.json");
        List<SellsyApiResponse> responseList = SellsyApiResponseManip.extractResponseList(inputRepsonse);
        assertEquals(0, responseList.size());
    }

    @Test
    public void testConvert() throws SellsyApiException, JsonParseException, JsonMappingException, IOException {
        SellsyApiResponse inputRepsonse = SellsyApiResponseManip.extractResponseList(responseFromFile("peopleListAnswer.json")).get(0);
        SellsyObject result = (SellsyObject) SellsyApiResponseManip.convert(inputRepsonse, SellsyObject.class);
        logger.debug(String.format("SellsyObject read : %s", result));
        assertTrue(result.getId().equals("1611782"));
    }

    
    @Test
    public void testConvertsubType() throws SellsyApiException, JsonParseException, JsonMappingException, IOException {
        SellsyApiResponse inputRepsonse = SellsyApiResponseManip.extractResponseList(responseFromFile("peopleListAnswer.json")).get(0);
        SellsyPeople result = (SellsyPeople) SellsyApiResponseManip.convert(inputRepsonse, SellsyPeople.class);
        logger.debug(String.format("SellsyObject read : %s", result));
        assertTrue(result.getId().equals("1611782"));
        assertTrue(result.getName().equalsIgnoreCase("Bouffart"));
        assertTrue(result.getOwnerfullname().equalsIgnoreCase("yves nicolas"));
    }
    
    private SellsyApiResponse responseFromFile(String path) throws JsonParseException, JsonMappingException, IOException {
        InputStream jsonInput =
                this.getClass().getClassLoader().getResourceAsStream(path);
            if (jsonInput == null) {
                throw new NullPointerException("can't find " + path);
            }
            
            
            Map<String,Object>  responseAsMap = OBJECTMAPPER.readValue(jsonInput, new TypeReference<Map<String,Object>>() {});
            return new SellsyApiResponse(responseAsMap);
 
    }
}
