package com.vjsai.intercom.store;

import com.vjsai.intercom.api.IDataStore;
import com.vjsai.intercom.exceptions.StoreException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class FileDataStore implements IDataStore {
    private  String outputFilename = "output.txt";
    private  String inputFilename = "customers.txt";

    public FileDataStore() {
    }

    public FileDataStore(String inputFilename, String outputFilename) {
        this.inputFilename = inputFilename;
        this.outputFilename = outputFilename;
    }

    public List<String> read(String input) throws StoreException {
        try{
            return Files.readAllLines(Paths.get(inputFilename));
        }catch (IOException exception){
            throw new StoreException(exception.getLocalizedMessage());
        }
    }

    @Override
    public Stream<String> readAsStream(String input) throws StoreException {
        try{
            return Files.lines(Paths.get(inputFilename));
        }catch (IOException exception){
            throw new StoreException(exception.getLocalizedMessage());
        }
    }

    public void write(List<String> records) throws StoreException {
        try{
            Files.write(Paths.get(outputFilename),records);
        }catch (IOException ex){
            throw new StoreException(ex.getLocalizedMessage());
        }

    }

    @Override
    public void writeStreamToFile(Stream<String> records) throws StoreException {
        try{
            Files.write(Paths.get(outputFilename), (Iterable<String>)records::iterator);
        }catch (IOException ex){
            throw new StoreException(ex.getLocalizedMessage());
        }
    }

    public void delete(String record) throws StoreException {

    }
}
