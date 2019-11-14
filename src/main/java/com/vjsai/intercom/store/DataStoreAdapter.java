package com.vjsai.intercom.store;

import com.vjsai.intercom.api.IDataStore;
import com.vjsai.intercom.exceptions.StoreException;

import java.util.List;
import java.util.stream.Stream;

/**
 * Acts as adapter to abstract the uderlying implementation
 */
public class DataStoreAdapter implements IDataStore {
    IDataStore store;

    public DataStoreAdapter(IDataStore store) {
        this.store = store;
    }

    public List<String> read(java.lang.String input) throws StoreException {
        return store.read(input);
    }

    @Override
    public Stream<String> readAsStream(String input) throws StoreException {
        return store.readAsStream(input);
    }

    public void write(List<String> records) throws StoreException {
        store.write(records);
    }

    @Override
    public void writeStreamToFile(Stream<String> records) throws StoreException {
        store.writeStreamToFile(records);
    }

    public void delete(String record) throws StoreException {
        store.delete(record);
    }
}
