/**
 * 
 */
package com.sellsy.coreConnector;

import java.util.List;

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
public static List<SellsyApiResponse> extractResponseList(SellsyApiResponse response) throws SellsyApiException {
    Map<String, Object> infos = response.getResponseAttribute("infos");
      if (infos == null)
          throw new SellsyApiException("Response is not a list : "+response.toString());
      else
          return null;
   }

}
