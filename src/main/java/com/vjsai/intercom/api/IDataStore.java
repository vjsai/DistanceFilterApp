package com.vjsai.intercom.api;

import com.vjsai.intercom.exceptions.StoreException;

import java.util.List;
import java.util.stream.Stream;

/**
 * Interface for store (ie., DB, File etc.)
 */
public interface IDataStore {
    /**
     * method to read data from
     *
     * @param input
     * @return
     * @throws StoreException
     */
    List<String> read(String input) throws StoreException;

    /**
     * method to read data as stream
     * @param input
     * @return
     * @throws StoreException
     */
    Stream<String> readAsStream(String input) throws StoreException;

    /**
     * Method to write data to
     *
     * @param records
     * @throws StoreException
     */
    void write(List<String> records) throws StoreException;

    /**
     * Method to write stream to file
     *
     * @param records
     * @throws StoreException
     */
    void writeStreamToFile(Stream<String> records) throws StoreException;

    /**
     * Method to delete the record
     *
     * @param record
     * @throws StoreException
     */
    void delete(String record) throws StoreException;
}
