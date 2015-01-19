package com.sellsy.utils;

public interface SellsyRequestExecutor {

    public abstract String submit(String method, String params);

}