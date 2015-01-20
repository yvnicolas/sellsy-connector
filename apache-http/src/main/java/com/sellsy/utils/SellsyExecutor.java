package com.sellsy.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SellsyExecutor {
    
    private static final URI uri;

    private static final Logger logger = LoggerFactory.getLogger(SellsyExecutor.class);
    
    private static final ObjectMapper OBJECTMAPPER = new ObjectMapper();
    
    private final  CloseableHttpClient httpclient = HttpClients.createDefault();
    
    private static final   ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

        public String handleResponse(
                final HttpResponse response) throws ClientProtocolException, IOException {
            int status = response.getStatusLine().getStatusCode();
            logger.debug("Status Line {}", response.getStatusLine().toString());
            if (status >= 200 && status < 300) {
                HttpEntity entity = response.getEntity();
                return entity != null ? EntityUtils.toString(entity) : null;
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
        }

    };
    

    static {
        try {
            uri = new URIBuilder().setScheme("https").setHost("apifeed.sellsy.com").setPath("/0").build();
        } catch (URISyntaxException e) {
            throw new RuntimeException("Malformed Uri " + e.toString(), e);
        }
    }
    
    /**
     * 
     */
    protected SellsyExecutor() {
        // TODO Auto-generated constructor stub
    }

    protected String submit(HttpUriRequest request) throws ClientProtocolException, IOException {
        logger.debug(String.format("Submitting request to sellsy : %s", request.getRequestLine()));
        return httpclient.execute(request, responseHandler);
    }
    

    public String submit(String method, Object params) throws ClientProtocolException, IOException {
        HttpPost postRequest = new HttpPost(uri);
        postRequest.addHeader("Authorization", oauthString());
        postRequest.addHeader("Content-Type", "multipart/form-data");
        postRequest.addHeader("Accept","text/plain, application/json, application/*+json, */*");
        HttpEntity reqEntity = MultipartEntityBuilder.create()
                .addPart("request", new StringBody("1", ContentType.TEXT_PLAIN))
                .addPart("io_mode", new StringBody("json", ContentType.TEXT_PLAIN))
                .addPart("do_in", new StringBody(OBJECTMAPPER.writeValueAsString(new SellsyApiCall(method, params)), ContentType.TEXT_PLAIN))
                .build();
              logger.debug(String.format("Entity : ", reqEntity.toString()));
        postRequest.setEntity(reqEntity);
      
        return submit(postRequest);
       
        
    }
    
    private String oauthString() {
        StringBuilder sb = new StringBuilder("Oauth ");
        sb.append("oauth_consumer_key=");
        sb.append("2b0ad3000cac95ca2a73b81b4adabe72d0b94e57");
        sb.append(",oauth_token=");
        sb.append("816deb4e9946d421ed8b3b50230503ba0baa6cdf");
        sb.append(",oauth_nonce=");
        long CurrentTime = new Date().getTime();
        String HashTime = DigestUtils.md5Hex(String.format("%s",CurrentTime+250));
        sb.append(HashTime);
        sb.append(",oauth_timestamp=");
        sb.append(CurrentTime);
        
        sb.append(",oauth_version=1.0,oauth_signature_method=PLAINTEXT");
        sb.append(",oauth_signature=");
        try {
            sb.append(URLEncoder.encode("181a01c3eac529008d9507b968c3b5a80ee4f5e7","UTF-8"));
            sb.append("&");
            sb.append(URLEncoder.encode("00d75564839d21dee76fcc9b32948dd5012a3078","UTF-8"));
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return sb.toString();
    }
}
