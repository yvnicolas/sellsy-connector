package com.sellsy.objectentities;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sellsy.SellsyTestUtils;
import com.sellsy.coreConnector.SellsyApiException;
import com.sellsy.coreConnector.SellsyApiResponse;

public class SellsyPeopleTest {

    private static final Logger logger = LoggerFactory.getLogger(SellsyPeopleTest.class);

    @Test
    public void test() throws SellsyApiException, IOException {
        String JsonString = SellsyTestUtils.responseFromFile("peopleGetOneAnswer.json");
        SellsyApiResponse apiResponse = new SellsyApiResponse(JsonString);
        SellsyPeople person = new SellsyPeople(apiResponse);
        logger.debug("Read SellsyPeople  {}", person);
        assertEquals("VAQUEZ", person.getName());
        assertEquals("Gérard", person.getForename());
    }

    @Test
    @Ignore
    //TODO to be reintegrated when complex array attributes are taken into account properly.
    public void testComplexAttribute() throws IOException, SellsyApiException {
        String JsonString = SellsyTestUtils.responseFromFile("vaquez.json");
        SellsyApiResponse apiResponse = new SellsyApiResponse(JsonString);
        SellsyPeople person = new SellsyPeople(apiResponse);
        String company = person.getAttributeValue("thirdList.name");
        logger.debug("Société : {}", company);
        assertEquals("Avenir Conseil", company);
    }
}
