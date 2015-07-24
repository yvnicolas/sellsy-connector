package com.sellsy.coreConnector;

import com.fasterxml.jackson.databind.ObjectMapper;

public final class SellsyAPIConstants {
    
    

    protected static final ObjectMapper OBJECTMAPPER = new ObjectMapper();
    
    
    // Attributes names reused several times in the API
    public static final String INFOS="infos";
    public static final String RESULT="result";
    public static final String ID="id";
    public static final String OWNERID="ownerid";

}
