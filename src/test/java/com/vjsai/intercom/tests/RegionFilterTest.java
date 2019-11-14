package com.vjsai.intercom.tests;

import com.vjsai.intercom.dto.Coordinates;
import com.vjsai.intercom.filter.RegionFilter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class RegionFilterTest {

    String[] expectedArr1 = {"{\"latitude\": \"51.92893\", \"user_id\": 1, \"name\": \"Alice Cahill\", \"longitude\": \"-10.27699\"}"};
    List<String> expectedList1 = Arrays.asList(expectedArr1);


    String[] expectedArr2 = {"{\"latitude\": \"52.986375\", \"user_id\": 12, \"name\": \"Navya Reddy\", \"longitude\": \"-6.043701\"}"};
    List<String> expectedList2 = Arrays.asList(expectedArr2);



    @Test
    public void withinDistance() throws Exception {
        List<String> filter2 = RegionFilter.filter(expectedList2);
        assertEquals(expectedList2.get(0).contains("Navya Reddy"),filter2.get(0).contains("Navya Reddy"));
    }

    @Test
    public void outsideDistance() throws Exception {
        List<String> filter1 = RegionFilter.filter(expectedList1);
        assertEquals(new ArrayList<String>(),filter1);
    }
}
