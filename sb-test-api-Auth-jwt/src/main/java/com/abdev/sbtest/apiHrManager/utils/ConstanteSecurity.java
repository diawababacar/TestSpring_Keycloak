package com.abdev.sbtest.apiHrManager.utils;

public class ConstanteSecurity {
    public static String REQUEST_HEADER_PREFIX = "Authorization";
    public static String HEADER_TOKEN = "Bearer ";
    // La date d'expiration du token sera de 10 jous
    public static int NOMBRE_EXPIRATION = 10*24*3600*1000;
    public static String SECRET = "C@RT@L!NK2019";
}
