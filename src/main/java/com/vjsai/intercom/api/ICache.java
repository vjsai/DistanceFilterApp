package com.vjsai.intercom.api;

/**
 * Cache Interface
 *
 * @param <K>
 * @param <V>
 */
public interface ICache<K, V> {

    /**
     * method to set key in cache
     *
     * @param key
     * @param value
     */
    void set(K key, V value);

    /**
     * get value for specific key
     *
     * @param key
     * @return
     */
    V get(K key);

    /**
     * checks if the given key exists in the cache
     *
     * @param key
     * @return
     */
    boolean contains(K key);

    /**
     * Invalidate the cache of the user
     *
     * @param key
     */
    void invalidate(K key);

    /**
     * clears the cache
     */
    void clear();

}
