package com.sellsy.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SellsyreqTest {

    private static final Logger logger = LoggerFactory.getLogger(SellsyreqTest.class);

    /**
     * Initial playground test.
     * kept for reference
     * @throws URISyntaxException
     * @throws IOException
     */
    @Ignore
    @Test
    public void test() throws URISyntaxException, IOException {

        URI uri = new URIBuilder().setScheme("https").setHost("apifeed.sellsy.com").setPath("/0/").build();

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(uri);
        logger.debug("Performing get on {}", httpget.getURI().toString());
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpget);

            logger.debug("Response status {}", response.getStatusLine().toString());
            logger.debug("Response {}", response.toString());
            logger.debug("{}", response.getEntity().getContentType().toString());
            InputStream is = response.getEntity().getContent();
            Scanner scan = new Scanner(is);
            while (scan.hasNext())
                logger.debug("{}", scan.nextLine());
            scan.close();

        } catch (IOException e) {
            logger.error("Exception raisded {}", e.toString());
        } finally {
            response.close();
        }

//        fail("Not yet implemented");
    }
    @Test
    public void test2() throws ClientProtocolException, IOException {
        Sellsyreq underTest = new Sellsyreq("bidon");
        logger.debug(String.format("Resultat : %s", underTest.execute()));
   
    }

}
