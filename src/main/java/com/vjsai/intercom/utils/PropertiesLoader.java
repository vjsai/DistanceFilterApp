package com.vjsai.intercom.utils;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {
    private static final String PROPERTIES_FILE_PATH = "app.properties";
    private static Properties configuration = new Properties();

    public static Properties loadProperties(String resourceFileName) throws IOException {
        InputStream inputStream = PropertiesLoader.class
          .getClassLoader()
          .getResourceAsStream(resourceFileName);
        configuration.load(inputStream);
        inputStream.close();
        return configuration;
    }

    public static Properties init(){
        try{
            if(configuration != null){
                loadProperties(PROPERTIES_FILE_PATH);
            }
        }catch (IOException ex){
            System.out.println("Unable to load  :");
        }
        return configuration;
    }


}
