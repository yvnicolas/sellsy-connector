package com.sellsy.coreConnector;

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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Implements an Sellsy Api call request executor for a private application
 * 
 * @author yves
 *
 */
public class SellsySpringRestExecutor implements SellsyRequestExecutor {

    private static final Logger logger = LoggerFactory.getLogger(SellsySpringRestExecutor.class);

    private static final String APIURI = "https://apifeed.sellsy.com/0/";

    private static final ObjectMapper OBJECTMAPPER = new ObjectMapper();

    // String constants used in Sellsy API Calls
    private static final String SUCCESS = "success";
    private static final String RESPONSE = "response";

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

    /**
     * Build Spring Authorization header for a private application
     * 
     * @return
     */
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
            logger.error("Exception raised encoding consumer and user secret {}", e.toString());
        }

        return sb.toString();
    }

    @SuppressWarnings("unchecked")
    @Override
    public SellsyApiResponse process(SellsyApiRequest request) throws SellsyApiException {

        // Building the request
        MultiValueMap<String, Object> sellssyCall = new LinkedMultiValueMap<>();
        sellssyCall.add("request", 1);
        sellssyCall.add("io_mode", "json");
        try {
            sellssyCall.add("do_in", OBJECTMAPPER.writeValueAsString(request));
        } catch (JsonProcessingException e) {
            throw new SellsyApiException(String.format("Sellsy Api request building : %s", e.toString()), e);
        }

        // Actual API Call to Selssy
        logger.debug("Submitting form value map {}", sellssyCall.toString());
        logger.debug("Headers : {}", headers.toString());
        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<MultiValueMap<String, Object>>(sellssyCall,
                this.headers);
        try {
            ResponseEntity<String> result = restTemplate.exchange(APIURI, HttpMethod.POST, entity, String.class);
            logger.debug("Result headers : {}", result.getHeaders().toString());
            logger.debug("Result body : {}", result.getBody());

            // Process the answer
            try {

                Map<String, Object> rawResponse = (Map<String, Object>) OBJECTMAPPER.readValue(result.getBody(),
                        Map.class);

                if (!rawResponse.get("status").equals(SUCCESS))
                    throw new SellsyApiException("Call to " + request.getMethod() + " : " + rawResponse.get("error"));
                else {
                    return new SellsyApiResponse((Map<String, Object>) rawResponse.get(RESPONSE));

                }

            } catch (IOException e) {
                String errorMsg = "Exception " + e.toString() + " raised decoding Sellsy Api rawResponse"
                        + result.getBody();
                logger.error(errorMsg);
                throw new SellsyApiException(errorMsg, e);
            }
        } catch (HttpClientErrorException e) {
            throw new SellsyApiException("Unable to connect to Sellsy server : " + e.getMessage(), e);
        }

    }

}
