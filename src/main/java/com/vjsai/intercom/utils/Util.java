package com.vjsai.intercom.utils;

import com.vjsai.intercom.dto.Coordinates;

import static java.lang.Math.acos;
import static java.lang.Math.toRadians;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Util {
    private static final int RADIUS_OF_EARTH_IN_KILOMETERS = 6371;

    /**
     * Computed as per the wiki link given
     * @param firstLocation
     * @param secondLocation
     * @return
     */
    public static double computeSphericalDistance(Coordinates firstLocation, Coordinates secondLocation) {
        double phi1 = toRadians(firstLocation.getLatitude());
        double lambda1 = toRadians(firstLocation.getLongitude());
        double phi2 = toRadians(secondLocation.getLatitude());
        double lambda2 = toRadians(secondLocation.getLongitude());

        double deltaSigma = acos((sin(phi1) * sin(phi2)) +
                (cos(phi1) * cos(phi2) * cos(Math.abs(lambda1 - lambda2))));

        return deltaSigma * RADIUS_OF_EARTH_IN_KILOMETERS;
    }
}
