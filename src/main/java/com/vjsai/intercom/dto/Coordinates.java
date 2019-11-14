package com.vjsai.intercom.dto;

import java.io.Serializable;

/**
 * Coordinates are serializable
 */
public class Coordinates implements Serializable {
    private double latitude;
    private double longitude;

    public Coordinates(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
