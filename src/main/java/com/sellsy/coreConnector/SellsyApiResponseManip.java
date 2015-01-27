/**
 * 
 */
package com.sellsy.coreConnector;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 
 * A collection of static method to manipulate Sellsy ApiResponse.
 * To be enhanced
 * @author yves
 *
 */
public class SellsyApiResponseManip {

   /**
    * Extract as a list all object response from a multi valued response
 * @param response
 * @return
 */
@SuppressWarnings("unchecked")
public static List<SellsyApiResponse> extractResponseList(SellsyApiResponse response) throws SellsyApiException {
    
    Map<String, Object> infos = (Map<String, Object>) response.getResponseAttribute("infos");
      if (infos == null)
          throw new SellsyApiException("Response is not a list : "+response.toString());
      else {
          List<SellsyApiResponse> toReturn = new ArrayList<>();
          Map<String, Object> responseList = (Map<String, Object>) response.getResponseAttribute("result");
          if (responseList != null) {
              for (String id : responseList.keySet()) {
                  toReturn.add(new SellsyApiResponse((Map<String, Object>) responseList.get(id)));
              }
          }
          return toReturn;
      }
   }

/**
 * @param response
 * @param typedObject
 * @return
 */
public static <T> T convert(SellsyApiResponse response, T typedObject) {
    
    return typedObject;
    
}
}
