package com.sellsy.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
//     List<MediaType> medias= new ArrayList<>(2);
//     medias.add(MediaType.APPLICATION_JSON);
//     medias.add(MediaType.parseMediaType("text/html"));
//       headers.setAccept(medias);
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
    }
    
    
    //TODO : passer en string pour essayer de passer, on pourra jouer avec les message converters pour Sellsyreq

    
    /* (non-Javadoc)
     * @see com.sellsy.utils.SellsyRequestExecutor#submit(java.lang.String, java.lang.String)
     */
    @SuppressWarnings("rawtypes")
    @Override
    public String submit(String method, Object params) {
        
        MultiValueMap<String, Object> sellssyCall = new LinkedMultiValueMap<>();
       sellssyCall.add("request", 1);
       sellssyCall.add("io_mode", "json");
       try {
        sellssyCall.add("do_in",OBJECTMAPPER.writeValueAsString(new SellsyApiCall(method, params)));
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
