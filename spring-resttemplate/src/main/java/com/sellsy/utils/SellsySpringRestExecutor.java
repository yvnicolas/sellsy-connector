package com.sellsy.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SellsySpringRestExecutor implements SellsyRequestExecutor {

    private static final Logger logger = LoggerFactory.getLogger(SellsySpringRestExecutor.class);
    
    private static final String APIURI = "https://apifeed.sellsy.com/0/";
    
    private static final ObjectMapper OBJECTMAPPER = new ObjectMapper();
  
    
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
        headers.setContentType(MediaType.APPLICATION_JSON);
    }
    
    
    //TODO : passer en string pour essayer de passer, on pourra jouer avec les message converters pour Sellsyreq

    
    /* (non-Javadoc)
     * @see com.sellsy.utils.SellsyRequestExecutor#submit(java.lang.String, java.lang.String)
     */
    @Override
    public String submit(String method, String params) {
        
        Sellsyreq request = new Sellsyreq(new SellsyApiCall(method, params));
        logger.debug("Submitting request {}", request.toString());
        logger.debug("Headers : {}", headers.toString());
        HttpEntity<String> entity;
        try {
            entity = new HttpEntity<String>(OBJECTMAPPER.writeValueAsString(request), this.headers);
        } catch (JsonProcessingException e) {
            logger.error("raised exception converting sellsyreq {}", e.toString());
            entity = new HttpEntity<String>("", this.headers);
        }
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
        String HashTime = DigestUtils.md5Hex(String.format("%s",CurrentTime+250));
        sb.append(HashTime);
        sb.append(",oauth_timestamp=");
        sb.append(CurrentTime);
        
        sb.append(",oauth_version=1.0,oauth_signature_method=PLAINTEXT");
        sb.append(",oauth_signature=");
        try {
            sb.append(URLEncoder.encode(this.consumerSecret,"UTF-8"));
            sb.append("&");
            sb.append(URLEncoder.encode(this.userSecret,"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return sb.toString();
    }

    
//    private String oauthString() {
//        StringBuilder sb = new StringBuilder("Oauth ");
//        sb.append("oauth_consumer_key=");
//        sb.append("2b0ad3000cac95ca2a73b81b4adabe72d0b94e57");
//        sb.append(",oauth_token=");
//        sb.append("816deb4e9946d421ed8b3b50230503ba0baa6cdf");
//        sb.append(",oauth_nonce=");
//        long CurrentTime = new Date().getTime();
//        String HashTime = DigestUtils.md5Hex(String.format("%s", CurrentTime + 250));
//        sb.append(HashTime);
//        sb.append(",oauth_timestamp=");
//        sb.append(CurrentTime);
//
//        sb.append(",oauth_version=1.0,oauth_signature_method=PLAINTEXT");
//        sb.append(",oauth_signature=");
//        try {
//            sb.append(URLEncoder.encode("181a01c3eac529008d9507b968c3b5a80ee4f5e7", "UTF-8"));
//            sb.append("&");
//            sb.append(URLEncoder.encode("00d75564839d21dee76fcc9b32948dd5012a3078", "UTF-8"));
//        } catch (UnsupportedEncodingException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        return sb.toString();
//    }

}
