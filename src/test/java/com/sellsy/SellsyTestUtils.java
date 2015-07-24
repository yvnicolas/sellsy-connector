/**
 * 
 */
package com.sellsy;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

/**
 * @author yves
 *
 */
public class SellsyTestUtils {

    
    public static String responseFromFile(String path) throws IOException {
        InputStream is = SellsyTestUtils.class.getClassLoader().getResourceAsStream(path);
        return IOUtils.toString(is);
    }
}
