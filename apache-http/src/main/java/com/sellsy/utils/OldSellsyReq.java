package com.sellsy.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstraction of a Sellsy api request pure test for the moment
 * 
 * @author Yves Nicolas
 * 
 */
public class OldSellsyReq {

//    private static final Logger logger = LoggerFactory.getLogger(OldSellsyReq.class);
//    
//  
//   
//   
//    private static final SellsyExecutor executor = new SellsyExecutor();
//
//
//   
//    static {
//        try {
//            uri = new URIBuilder().setScheme("https").setHost("apifeed.sellsy.com").setPath("/0").build();
//        } catch (URISyntaxException e) {
//            throw new RuntimeException("Malformed Uri " + e.toString(), e);
//        }
//    }
//    private String requestAsString;
//
//    /**
//     * @param request
//     */
//    public OldSellsyReq(String request) {
//        this.requestAsString = request;
//    }
//
//    public String execute() throws ClientProtocolException, IOException {
//        HttpPost postRequest = new HttpPost(uri);
//        postRequest.addHeader("Authorization", oauthString());
////        postRequest.addHeader("Content-Type", "application/json");
//        StringEntity entity = new StringEntity("{\"do_in\":{\"method\":\"Peoples.getList\",\"params\":{}},\"io_mode\":\"json\",\"request\":1}",
//                ContentType.APPLICATION_JSON);
//        logger.debug(String.format("Entity : ", EntityUtils.toString(entity, "UTF-8")));
//        postRequest.setEntity(entity);
//        return executor.submit(postRequest);
//       
//        
//    }
//    
//    private String oauthString() {
//        StringBuilder sb = new StringBuilder("Oauth ");
//        sb.append("oauth_consumer_key=");
//        sb.append("2b0ad3000cac95ca2a73b81b4adabe72d0b94e57");
//        sb.append(",oauth_token=");
//        sb.append("816deb4e9946d421ed8b3b50230503ba0baa6cdf");
//        sb.append(",oauth_nonce=");
//        long CurrentTime = new Date().getTime();
//        String HashTime = DigestUtils.md5Hex(String.format("%s",CurrentTime+250));
//        sb.append(HashTime);
//        sb.append(",oauth_timestamp=");
//        sb.append(CurrentTime);
//        
//        sb.append(",oauth_version=1.0,oauth_signature_method=PLAINTEXT");
//        sb.append(",oauth_signature=");
//        try {
//            sb.append(URLEncoder.encode("181a01c3eac529008d9507b968c3b5a80ee4f5e7","UTF-8"));
//            sb.append("&");
//            sb.append(URLEncoder.encode("00d75564839d21dee76fcc9b32948dd5012a3078","UTF-8"));
//        } catch (UnsupportedEncodingException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        
//        return sb.toString();
//    }

}
