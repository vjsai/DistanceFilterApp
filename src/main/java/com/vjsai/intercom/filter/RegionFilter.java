package com.vjsai.intercom.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vjsai.intercom.api.ICache;
import com.vjsai.intercom.cache.LRUCache;
import com.vjsai.intercom.dto.Coordinates;
import com.vjsai.intercom.dto.Customer;
import com.vjsai.intercom.utils.Util;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.vjsai.intercom.config.Config.CENTER;
import static com.vjsai.intercom.config.Config.DISTANCE_IN_KM;

/**
 * Class helps us filtering basing upon the distance configured
 */
public class RegionFilter {
    //flag to enable and disable usage of cache
    private static boolean useCache;

    //flag to filter out duplicate userIds
    private static boolean areDuplicatesAllowed = false;

    //Cache to hold the distance of userIds from center
    private static ICache cache;

    //ObjectMapper to serialize and deserialize the objects
    private static ObjectMapper mapper = new ObjectMapper();

    public static List<String> filter(List<String> customers) throws Exception {

        return filterStream(customers.stream()).collect(Collectors.toList());
    }

    public static Stream<String> filterStream(Stream<String> customers) {
        List<Customer> filteredCustomers = customers
                .map(customer -> convertToCustomer(customer))
                .filter(customer -> filterByDistance(customer))
                .collect(Collectors.toList());


        Collections.sort(filteredCustomers);

        return filteredCustomers.stream()
                .map((cus) -> convertToString(cus));
    }

    /**
     * Enable or disable caching of computed distances
     *
     * @param shouldCache
     */
    public static void useCache(boolean shouldCache) {
        useCache = shouldCache;
        if (useCache) {
            cache = new LRUCache();
            ((LRUCache) cache).setCapacity(1000);
        }
    }

    /**
     * allow or disallow duplicate userIds in the list
     *
     * @param duplicatesAllowed
     */
    public static void filterDuplicates(boolean duplicatesAllowed) {
        areDuplicatesAllowed = duplicatesAllowed;
    }

    /**
     * serilalize to string
     *
     * @param customer
     * @return
     */
    private static String convertToString(Customer customer) {
        try {
            return mapper.writeValueAsString(customer);
        } catch (JsonProcessingException jsonEx) {
            return "";
        }
    }

    /**
     * Deserialize to object
     *
     * @param cust
     * @return
     */
    private static Customer convertToCustomer(String cust) {
        try {
            return mapper.readValue(cust, Customer.class);
        } catch (JsonProcessingException jsonEx) {
            return null;
        }
    }


    /**
     * Method to fileterByDistance basing on enable of cache
     *
     * @param customer
     * @return
     */
    private static boolean filterByDistance(Customer customer) {

        Coordinates coordinates = new Coordinates(customer.getLatitude(), customer.getLongitude());
        Double sphericalDistance = null;
        //Just to showcase scenario where if the computation is an expensive operation how caching would have helped.
        Integer userId = customer.getUserId();
        if (useCache) {
            if (cache.contains(userId)) {
                //This flag would need to be modified in case we have multiple duplicates in the input file
                //If areDuplicatesAllowed is turned on it would keep on adding duplicates to the filtered list.
                //This would currently work only in case a cache is used.
                if (!areDuplicatesAllowed) {
                    return false;
                }
                sphericalDistance = (Double) cache.get(userId);
            } else {
                sphericalDistance = Util.computeSphericalDistance(CENTER, coordinates);
                cache.set(userId, sphericalDistance);
            }
        } else {
            sphericalDistance = Util.computeSphericalDistance(CENTER, coordinates);
        }
        if (sphericalDistance < DISTANCE_IN_KM) {
            return true;
        }
        return false;
    }

}
