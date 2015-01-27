package com.sellsy.apiservices;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sellsy.apientities.SellsyAPIMethod;
import com.sellsy.apientities.SellsyApiCall;

public class SellsySpringRestExecutor implements SellsyRequestExecutor {

    private static final Logger logger = LoggerFactory.getLogger(SellsySpringRestExecutor.class);

    private static final String APIURI = "https://apifeed.sellsy.com/0/";

    private static final ObjectMapper OBJECTMAPPER = new ObjectMapper();
    
    private static final String SUCCESS = "success";

    // Keys
    private String consumerToken;
    private String consumerSecret;
    private String userToken;
    private String userSecret;

    private RestTemplate restTemplate = new RestTemplate();
    private HttpHeaders headers = new HttpHeaders();

    public SellsySpringRestExecutor(String consumerToken, String consumerSecret, String userToken, String userSecret) {
        super();
        this.consumerToken = consumerToken;
        this.consumerSecret = consumerSecret;
        this.userToken = userToken;
        this.userSecret = userSecret;

        headers.set("Authorization", oauthString());
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sellsy.utils.SellsyRequestExecutor#submit(java.lang.String, java.lang.String)
     */
    @SuppressWarnings("rawtypes")
    @Override
    public String stringSubmit(String method, Object params) {

        MultiValueMap<String, Object> sellssyCall = new LinkedMultiValueMap<>();
        sellssyCall.add("request", 1);
        sellssyCall.add("io_mode", "json");
        try {
            sellssyCall.add("do_in", OBJECTMAPPER.writeValueAsString(new SellsyApiCall(method, params)));
        } catch (JsonProcessingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        logger.debug("Submitting form value map {}", sellssyCall.toString());
        logger.debug("Headers : {}", headers.toString());
        HttpEntity<MultiValueMap> entity = new HttpEntity<MultiValueMap>(sellssyCall, this.headers);

        ResponseEntity<String> result = restTemplate.exchange(APIURI, HttpMethod.POST, entity, String.class);
        logger.debug("Result headers : {}", result.getHeaders().toString());

        logger.debug("Result body : {}", result.getBody());
        return result.getBody();

    }

    private String oauthString() {
        StringBuilder sb = new StringBuilder("Oauth ");
        sb.append("oauth_consumer_key=");
        sb.append(this.consumerToken);
        sb.append(",oauth_token=");
        sb.append(this.userToken);
        sb.append(",oauth_nonce=");
        long CurrentTime = new Date().getTime();
        String HashTime = DigestUtils.md5Hex(String.format("%s", CurrentTime + 250));
        sb.append(HashTime);
        sb.append(",oauth_timestamp=");
        sb.append(CurrentTime);

        sb.append(",oauth_version=1.0,oauth_signature_method=PLAINTEXT");
        sb.append(",oauth_signature=");
        try {
            sb.append(URLEncoder.encode(this.consumerSecret, "UTF-8"));
            sb.append("&");
            sb.append(URLEncoder.encode(this.userSecret, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return sb.toString();
    }

   
    @SuppressWarnings("unchecked")
    @Override
    public Object typedSubmit(SellsyAPIMethod method, Object params) throws SellsyApiException {

        // Sellsy Api Call
        String responseAsString = stringSubmit(method.getName(), params);
        Map<String, Object> rawResponse = null;
        
        // Process error cases
        try {
            rawResponse = (Map<String, Object>) OBJECTMAPPER.readValue(responseAsString ,Object.class);
            if (!rawResponse.get("status").equals(SUCCESS))
                throw new SellsyApiException("Call to " + method.getName() + " : " + rawResponse.get("error"));
            else {
                Object mapResponse = rawResponse.get("response");
                if (mapResponse!=null)
                 return OBJECTMAPPER.convertValue(rawResponse.get("response"), method.getResponseClass());
                else return null;
                
            }
            
            
            
        } catch (IOException e) {
            String errorMsg = "Exception " + e.toString() + " raised decoding Sellsy Api rawResponse" + responseAsString;
            logger.error(errorMsg);
            throw new SellsyApiException(errorMsg, e);
        }
        
       
    
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> mapSubmit(String method, Map<String, Object> params) throws SellsyApiException {
        String responseAsString = stringSubmit (method, params);
        Map<String, Object> rawResponse = null;
        
        // Process error cases
        try {
            rawResponse = (Map<String, Object>) OBJECTMAPPER.readValue(responseAsString ,Object.class);
            if (!rawResponse.get("status").equals(SUCCESS))
                throw new SellsyApiException("Call to " + method + " : " + rawResponse.get("error"));
            else {
                return (Map<String, Object>) rawResponse.get("response");
                
            }
            
            
            
        } catch (IOException e) {
            String errorMsg = "Exception " + e.toString() + " raised decoding Sellsy Api rawResponse" + responseAsString;
            logger.error(errorMsg);
            throw new SellsyApiException(errorMsg, e);
        }
        
       
    
    }

}
