package com.vjsai.intercom.cache;

import com.vjsai.intercom.api.ILRUCache;
import org.omg.PortableInterceptor.INACTIVE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Building a LRU cache assuming the location of the user Id doesnt change
 */
public class LRUCache implements ILRUCache<Integer, Double> {

    private static final Logger LOGGER = LoggerFactory.getLogger(LRUCache.class);

    class Node {
        Integer userId;
        double geometricalDistance;
        Node prev;
        Node next;

        public Node(Integer userId, double geometricalDistance) {
            this.userId = userId;
            this.geometricalDistance = geometricalDistance;
        }
    }

    //variables required for creating LRU cache
    Node head;
    Node end;
    int capacity;
    Map<Integer, Node> cache = new HashMap<Integer, Node>();

    // LinkedList related methods

    /**
     * Move node to the front of the list.
     */
    private void setHead(Node node) {
        node.next = head;
        node.prev = null;
        if (head != null) {
            head.prev = node;
        }
        head = node;
        if (end == null) {
            end = head;
        }
    }


    /**
     * Remove node from linked list.
     */
    private void remove(Node node) {
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            head = node.next;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            end = node.prev;
        }
    }

    //HashMap and LRU related methods

    /**
     * check if the cache is full or not
     *
     * @return
     */
    public boolean isFull() {
        return cache.size() >= capacity;
    }

    /**
     * Invalidate cache of the customer.
     */
    public void invalidate(Integer userId) {
        Node toBeRemoved = cache.remove(userId);
        if (toBeRemoved != null) {
            LOGGER.info("{} is removed from cache", userId);
            remove(toBeRemoved);
        }
    }

    /**
     * checks in hashMap if the userId exists
     *
     * @param userId
     * @return
     */
    public boolean contains(Integer userId) {
        return cache.containsKey(userId);
    }


    /**
     * clear the cache
     */
    public void clear() {
        head = null;
        end = null;
        cache.clear();
    }

    /**
     * To change the capacity of cache
     *
     * @param newCapacity
     */
    public void setCapacity(int newCapacity) {
        if (capacity > newCapacity) {
            clear(); // just clear the cache.
        } else {
            this.capacity = newCapacity;
        }
    }


    /**
     * to cache computed sphericalDistance
     *
     * @param userId
     * @param sphericalDistance
     */
    public void set(Integer userId, Double sphericalDistance) {
        if (cache.containsKey(userId)) {
            Node old = cache.get(userId);
            old.geometricalDistance = sphericalDistance;
            remove(old);
            setHead(old);
        } else {
            Node newNode = new Node(userId, sphericalDistance);
            if (cache.size() >= capacity) {
                LOGGER.info("Cache is FULL.So Removing {} from cache", end.userId);
                cache.remove(end.userId); // remove LRU data from cache.
                remove(end);
                setHead(newNode);
            } else {
                setHead(newNode);
            }
            cache.put(userId, newNode);
        }
    }


    /**
     * Get already computed distance from cache
     */
    public Double get(Integer userId) {
        if (cache.containsKey(userId)) {
            Node node = cache.get(userId);
            remove(node);
            setHead(node);
            return node.geometricalDistance;
        }
        return null;
    }

    /**
     * get the data in last node
     *
     * @return
     */
    public Double getLruData() {
        return end.geometricalDistance;
    }


}
