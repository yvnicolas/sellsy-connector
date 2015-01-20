package com.sellsy.utils;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sellsy.entity.ListGetParams;
import com.sellsy.entity.Pagination;
import com.sellsy.entity.SearchFilter;

public class SellsyreqTest {

    private static final Logger logger = LoggerFactory.getLogger(SellsyreqTest.class);
    
    private static final SellsyExecutor underTest = new SellsyExecutor();

    @Test
    public void test() throws ClientProtocolException, IOException {    
        ListGetParams params = new ListGetParams();
        params.setPagination(new Pagination(10, 1));
        params.setSearch(new SearchFilter("610278087"));
        String result = underTest.submit("Peoples.getList", null);
        logger.info("Retour : {}", result);
    }
}
