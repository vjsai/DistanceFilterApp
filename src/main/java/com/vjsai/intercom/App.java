package com.vjsai.intercom;

import com.vjsai.intercom.filter.RegionFilter;
import com.vjsai.intercom.store.DataStoreAdapter;
import com.vjsai.intercom.store.FileDataStore;
import com.vjsai.intercom.utils.PropertiesLoader;

import java.util.List;
import java.util.Properties;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args){
        try{
            long startTime = System.nanoTime();
            //load configuration from property file
            Properties configuration = PropertiesLoader.init();

            //load inputfile and outfile path from configuration provided in app.properties
            FileDataStore fileStore = new FileDataStore(configuration.getProperty("inputFile"),configuration.getProperty("outputFile"));

            DataStoreAdapter store = new DataStoreAdapter(fileStore);

            //load file as stream
            Stream<String> customerStream = store.readAsStream("cusomers.txt");

            //enable cache
            RegionFilter.useCache(true);

            //filter basing upon distance
            Stream<String> filtered = RegionFilter.filterStream(customerStream);

            //Write it to a file
            store.writeStreamToFile(filtered);
            long endTime = System.nanoTime();
            System.out.println("Time taken to execute is : "+(endTime - startTime));
        }catch (Exception ex){
            System.out.println(ex.getLocalizedMessage());
        }
    }
}
