package com.vjsai.intercom.tests;

import com.vjsai.intercom.exceptions.StoreException;
import com.vjsai.intercom.store.FileDataStore;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class FileDataStoreTest {

    FileDataStore fileDataStore1;
    FileDataStore fileDataStore2;


    String[] expectedArr1 = {"{\"latitude\": \"51.92893\", \"user_id\": 1, \"name\": \"Alice Cahill\", \"longitude\": \"-10.27699\"}"};
    List<String> expectedList1 = Arrays.asList(expectedArr1);


    String[] expectedArr2 = {"{\"latitude\": \"52.986375\", \"user_id\": 12, \"name\": \"Navya Reddy\", \"longitude\": \"-6.043701\"}"};
    List<String> expectedList2 = Arrays.asList(expectedArr2);


    @Before
    public void setUp() throws Exception {
        fileDataStore1 = new FileDataStore("src/test/resources/customeroutsiderange.txt","src/test/resources/output1.txt");
        fileDataStore2 = new FileDataStore("src/test/resources/customerwithinrange.txt","src/test/resources/output2.txt");
    }

    @After
    public void tearDown() throws Exception {
        Files.deleteIfExists(Paths.get("src/test/resources/output1.txt"));
        Files.deleteIfExists(Paths.get("src/test/resources/output2.txt"));
    }

    @Test
    public void testReadFile() throws StoreException {

        assertEquals(expectedList1,fileDataStore1.read(""));

        assertEquals(expectedList2,fileDataStore2.read(""));

    }


    @Test
    public void testWriteFile() throws StoreException, IOException {
        fileDataStore1.write(expectedList1);

        fileDataStore2.write(expectedList2);

        assertEquals(expectedList1, Files.readAllLines(Paths.get("src/test/resources/output1.txt")));

        assertEquals(expectedList2, Files.readAllLines(Paths.get("src/test/resources/output2.txt")));

    }
}
