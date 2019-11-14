package com.vjsai.intercom.tests;

import com.vjsai.intercom.dto.Coordinates;
import com.vjsai.intercom.utils.Util;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SphericalDistanceTest {

    Coordinates coordinates1 = new Coordinates(53.339428,-6.257664);
    Coordinates coordinates2 =  new Coordinates(53.339428,-7.257664);
    Coordinates coordinates3 =  new Coordinates(53.339428,-8.257664);
    //calculated from http://www.onlineconversion.com/map_greatcircle_distance.htm
    double actual_distance_12 = 66.39097562546188;
    double actual_distance_23 = 66.39097562546188;
    double actual_distance_13 = 132.77869755827547;

    @Test
    public void testSamePoint(){
        assertEquals(Util.computeSphericalDistance(coordinates1,coordinates1),0,0);
    }

    @Test
    public void testDifferentPoints(){
        assertEquals(Util.computeSphericalDistance(coordinates1,coordinates2),actual_distance_12,0.0001);
        assertEquals(Util.computeSphericalDistance(coordinates3,coordinates2),actual_distance_23,0.0001);
        assertEquals(Util.computeSphericalDistance(coordinates1,coordinates3),actual_distance_13,0.0001);
        //approx mid point
        assertEquals(actual_distance_12+actual_distance_23,actual_distance_13,0.01);
    }

}
