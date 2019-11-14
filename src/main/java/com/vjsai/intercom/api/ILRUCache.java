package com.vjsai.intercom.api;

/**
 * Interface for LRUCache
 *
 * @param <K>
 * @param <V>
 */
public interface ILRUCache<K, V> extends ICache<K, V> {
    /**
     * to get the last node in LRU
     *
     * @return
     */
    V getLruData();

    /**
     * setting capacity of the LRU cache
     *
     * @param newCapacity
     */
    void setCapacity(int newCapacity);

    /**
     * checks if the LRU cache is full or not
     *
     * @return
     */
    boolean isFull();
}
