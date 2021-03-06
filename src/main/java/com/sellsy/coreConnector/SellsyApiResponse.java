/**
 * 
 */
package com.sellsy.coreConnector;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.sellsy.apientities.SellsyResponseInfo;

/**
 * Encapsulation of the response given by the Sellsy API
 * 
 * @author yves
 *
 */
public class SellsyApiResponse {

    private static final Logger logger = LoggerFactory.getLogger(SellsyApiResponse.class);
    private static final String DOT = ".";

    private JsonNode responseAsTree;

    /**
     * @throws SellsyApiException
     * 
     */

    public SellsyApiResponse(String responseAsString) throws SellsyApiException {
        try {
            responseAsTree = SellsyAPIConstants.OBJECTMAPPER.readTree(responseAsString);
        } catch (IOException e) {
            throw new SellsyApiException(String.format("Unable to read as a sellsy api response : %s - : %s",
                    responseAsString, e.toString(), e));
        }
    }

    protected SellsyApiResponse(JsonNode responseAsTree) {
        this.responseAsTree = responseAsTree;
    }
    
    public SellsyApiResponse getAttributeValueAsResponse(String attribute)throws SellsyApiException {
        checkvoid();
        return new SellsyApiResponse(responseAsTree.get(attribute));
    }

    /**
     * Find the value of a complete attribute in the tree structure for the response. Example, to
     * get the company name of e contact, you would try "thirdList.name"
     * 
     * @param attribute
     * @return
     */
    public String getResponseAttribute(String attribute) {
        return attrvalue(attribute, responseAsTree);
    }

    public Set<String> firstLevelAttributesList() throws SellsyApiException {
        checkvoid();
        Set<String> toReturn = new HashSet<String>();
        Iterator<Entry<String, JsonNode>> index = responseAsTree.fields();
        while (index.hasNext())
            toReturn.add(index.next().getKey());
        return toReturn;
    }

    public Set<String> allLevelAttributesList() throws SellsyApiException {
        checkvoid();
        return allLevelAttributesList(responseAsTree);
    }

    public Set<String> allLevelAttributesList(JsonNode jsonNode) {

        Set<String> toReturn = new HashSet<String>();
        Iterator<Entry<String, JsonNode>> index = jsonNode.fields();
        while (index.hasNext()) {
            Entry<String, JsonNode> current = index.next();
            if (current.getValue().isContainerNode())
                for (String subLevelString : allLevelAttributesList(current.getValue()))
                    toReturn.add(current.getKey() + DOT + subLevelString);
            else
                toReturn.add(current.getKey());
        }

        return toReturn;

    }
    
    //TODO : this doesnot take into account array nodes. SHould put more thinking in that.

    private String attrvalue(String attribute, JsonNode jsonNode) {

        int firstDot = attribute.indexOf('.');

        // Only one attribute
        if (firstDot == -1) {
            JsonNode current = jsonNode.get(attribute);
            if (current == null)
                return null;
            switch (current.getNodeType()) {

            case MISSING:
            case NULL:
                return null;
            case STRING:
            case BOOLEAN:
            case NUMBER:
                return current.asText();

            case OBJECT:
            case POJO:               
                return current.toString();
            default:
                return null;

            }
        }

        // recursively apply
        else {
            String firstAttribute = attribute.substring(0, firstDot);
            String remainingAttributes = attribute.substring(firstDot + 1, attribute.length());
            return attrvalue(remainingAttributes, jsonNode.path(firstAttribute));
        }

    }

    @Override
    public String toString() {
        try {
            return SellsyAPIConstants.OBJECTMAPPER.writeValueAsString(responseAsTree);
        } catch (JsonProcessingException e) {
            return responseAsTree.toString();
        }
    }

    public SellsyResponseInfo getInfos() throws SellsyApiException {
        checkvoid();
        try {
            return SellsyAPIConstants.OBJECTMAPPER.treeToValue(responseAsTree.get(SellsyAPIConstants.INFOS),
                    SellsyResponseInfo.class);
        } catch (JsonProcessingException e) {
            throw new SellsyApiException("Unable to get infos", e);
        }
    }

    /**
     * Used by all other methods to make sure the json representation is not empty
     * 
     * @throws SellsyApiException
     */
    private void checkvoid() throws SellsyApiException {
        if (responseAsTree == null)
            throw new SellsyApiException("Trying to extract data from a void sellsy Api response");

    }

    /**
     * For list response, get back the individual response lists,
     * 
     * @return
     * @throws SellsyApiException
     */
    public List<SellsyApiResponse> extractResponseList() throws SellsyApiException {

        checkvoid();

        JsonNode result = responseAsTree.get(SellsyAPIConstants.RESULT);
        if (result == null)
            throw new SellsyApiException(String.format("Response is not a list : %s", responseAsTree.toString()));
        List<SellsyApiResponse> toReturn = new ArrayList<>();
        Iterator<Entry<String, JsonNode>> index = result.fields();
        while (index.hasNext())
            toReturn.add(new SellsyApiResponse(index.next().getValue()));

        return toReturn;
    }

}
