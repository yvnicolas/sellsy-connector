package com.sellsy.utils;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SellsyExecutor {

    private static final Logger logger = LoggerFactory.getLogger(SellsyExecutor.class);
    
    private final  CloseableHttpClient httpclient = HttpClients.createDefault();
    
    private static final   ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

        public String handleResponse(
                final HttpResponse response) throws ClientProtocolException, IOException {
            int status = response.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity entity = response.getEntity();
                return entity != null ? EntityUtils.toString(entity) : null;
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
        }

    };
    
    /**
     * 
     */
    protected SellsyExecutor() {
        // TODO Auto-generated constructor stub
    }

    protected String submit(HttpUriRequest request) throws ClientProtocolException, IOException {
        logger.debug(String.format("Submitting request to sellsy : %s", request.toString()));
        return httpclient.execute(request, responseHandler);
    }
}
