package com.vjsai.intercom.config;

import com.vjsai.intercom.dto.Coordinates;

public class Config {
    public static Coordinates CENTER = new Coordinates(53.339428,-6.257664);
    public static int DISTANCE_IN_KM = 100;
    public static boolean IS_CACHE_ENABLED = true;
    public static String STORE_TYPE = "FILE";
    public static String OUTPUT_FILENAME = "output.txt";
    public static String INPUT_FILENAME = "customers.txt";
}
