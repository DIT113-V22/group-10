package com.example.hermes;/*ckage com.example.hermes;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

import static junit.framework.Assert.assertEquals;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class ExampleUnitTest {

    JsonManager jManager = new JsonManager();
    ArrayList<Delivery> deliveries = new ArrayList<>();
    Delivery delivery1 = new Delivery();
    Delivery delivery2 = new Delivery();
    Delivery delivery3 = new Delivery();
    Delivery delivery4 = new Delivery();
    Delivery delivery5 = new Delivery();
    Delivery delivery6 = new Delivery();
    Delivery delivery7 = new Delivery();
    Delivery delivery8 = new Delivery();
    Delivery delivery9 = new Delivery();
    Delivery delivery10 = new Delivery();

    @Before
    public void setUp() throws ParseException {

        //Setup for Json tests
        delivery1.setCustomerID("Emrik");
        delivery1.setDate("20221104 13:45:00");

        //Setup for sorting tests
        deliveries.add(delivery1);
        delivery2.setCustomerID("Erik");
        delivery2.setDate("20110912 08:23:55");
        deliveries.add(delivery2);
        delivery3.setCustomerID("Amin");
        delivery3.setDate("20222104 13:45:00");
        deliveries.add(delivery3);
        delivery4.setCustomerID("nihiliss");
        delivery4.setDate("20221120 09:15:02");
        deliveries.add(delivery4);
        delivery5.setCustomerID("Julia");
        delivery5.setDate("20110612 08:23:55");
        deliveries.add(delivery5);
        delivery6.setCustomerID("Yasamin");
        delivery6.setDate("20220113 12:11:56");
        deliveries.add(delivery6);
        delivery7.setCustomerID("Daniel");
        delivery7.setDate("20220113 00:11:56");
        deliveries.add(delivery7);
        delivery8.setCustomerID("Sven");
        delivery8.setDate("19800627 16:00:17");
        deliveries.add(delivery8);
        delivery9.setCustomerID("Karl");
        delivery9.setDate("20240520 09:35:08");
        deliveries.add(delivery9);
        delivery10.setCustomerID("Björn");
        delivery10.setDate("20220512 23:13:44");
        deliveries.add(delivery10);

    }

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void gettingJsonObject(){
        JSONObject actualObject = jManager.readJsonObject("src/main/java/com/example/hermes/data/test.Json");
        JSONObject expectedObject = createExpectedObject();

        assertEquals(expectedObject.toString(), actualObject.toString());
    }

    @Test
    public void writeJson() throws JSONException {
        jManager.writeDelivery(delivery1, "src/main/java/com/example/hermes/data/test.Json");
        JSONObject jsonObject = jManager.readJsonObject("src/main/java/com/example/hermes/data/test.Json");
        JSONArray array = (JSONArray) jsonObject.get("deliveries");
        JSONObject expectedObject = createExpectedObject();

        try (FileWriter file = new FileWriter("src/main/java/com/example/hermes/data/test.Json")){
            file.write(expectedObject.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(array.get(0), array.get(1));
    }

    @Test
    public void sortDeliveries(){
        ArrayList<Delivery> oldest = new ArrayList<>(deliveries);
        oldest.sort(Delivery.byOldest);
        ArrayList<Delivery> newest = new ArrayList<>(deliveries);
        newest.sort(Delivery.byNewest);
        ArrayList<Delivery> a_z = new ArrayList<>(deliveries);
        a_z.sort(Delivery.byName);
        ArrayList<Delivery> z_a = new ArrayList<>(deliveries);
        z_a.sort(Delivery.byNameReverse);

        String expectedOldest = "Sven Fri Jun 27 16:00:17 EET 1980\n" +
                "Julia Sun Jun 12 08:23:55 EEST 2011\n" +
                "Erik Mon Sep 12 08:23:55 EEST 2011\n" +
                "Daniel Thu Jan 13 00:11:56 EET 2022\n" +
                "Yasamin Thu Jan 13 12:11:56 EET 2022\n" +
                "Björn Thu May 12 23:13:44 EEST 2022\n" +
                "Emrik Fri Nov 04 13:45:00 EET 2022\n" +
                "nihiliss Sun Nov 20 09:15:02 EET 2022\n" +
                "Amin Mon Sep 04 13:45:00 EEST 2023\n" +
                "Karl Mon May 20 09:35:08 EEST 2024\n";

        String expectedNewest = "Karl Mon May 20 09:35:08 EEST 2024\n" +
                "Amin Mon Sep 04 13:45:00 EEST 2023\n" +
                "nihiliss Sun Nov 20 09:15:02 EET 2022\n" +
                "Emrik Fri Nov 04 13:45:00 EET 2022\n" +
                "Björn Thu May 12 23:13:44 EEST 2022\n" +
                "Yasamin Thu Jan 13 12:11:56 EET 2022\n" +
                "Daniel Thu Jan 13 00:11:56 EET 2022\n" +
                "Erik Mon Sep 12 08:23:55 EEST 2011\n" +
                "Julia Sun Jun 12 08:23:55 EEST 2011\n" +
                "Sven Fri Jun 27 16:00:17 EET 1980\n";

        String expectedByName = "Amin Mon Sep 04 13:45:00 EEST 2023\n" +
                "Björn Thu May 12 23:13:44 EEST 2022\n" +
                "Daniel Thu Jan 13 00:11:56 EET 2022\n" +
                "Emrik Fri Nov 04 13:45:00 EET 2022\n" +
                "Erik Mon Sep 12 08:23:55 EEST 2011\n" +
                "Julia Sun Jun 12 08:23:55 EEST 2011\n" +
                "Karl Mon May 20 09:35:08 EEST 2024\n" +
                "nihiliss Sun Nov 20 09:15:02 EET 2022\n" +
                "Sven Fri Jun 27 16:00:17 EET 1980\n" +
                "Yasamin Thu Jan 13 12:11:56 EET 2022\n";

        String expectedByReverseName = "Yasamin Thu Jan 13 12:11:56 EET 2022\n" +
                "Sven Fri Jun 27 16:00:17 EET 1980\n" +
                "nihiliss Sun Nov 20 09:15:02 EET 2022\n" +
                "Karl Mon May 20 09:35:08 EEST 2024\n" +
                "Julia Sun Jun 12 08:23:55 EEST 2011\n" +
                "Erik Mon Sep 12 08:23:55 EEST 2011\n" +
                "Emrik Fri Nov 04 13:45:00 EET 2022\n" +
                "Daniel Thu Jan 13 00:11:56 EET 2022\n" +
                "Björn Thu May 12 23:13:44 EEST 2022\n" +
                "Amin Mon Sep 04 13:45:00 EEST 2023\n";

        String actualOldest = "";
        String actualNewest = "";
        String actualByName = "";
        String actualByReverseName = "";

        for (int i =0; i<10; i++){
            actualOldest += oldest.get(i).toString()+ "\n";
            actualNewest += newest.get(i).toString()+ "\n";
            actualByName += a_z.get(i).toString()+ "\n";
            actualByReverseName += z_a.get(i).toString()+ "\n";
        }
        assertEquals(expectedOldest, actualOldest);
        assertEquals(expectedNewest, actualNewest);
        assertEquals(expectedByName, actualByName);
        assertEquals(expectedByReverseName, actualByReverseName);
    }

    private JSONObject createExpectedObject(){
        JSONObject expectedObject = new JSONObject();
        JSONObject deliveryDetails = new JSONObject();
        JSONObject delivery = new JSONObject();
        JSONArray array = new JSONArray();

        deliveryDetails.put("date", "2022 Nov 04");
        deliveryDetails.put("customerID", "Emrik");
        deliveryDetails.put("time", "13:45:00");
        delivery.put("delivery", deliveryDetails);
        array.add(delivery);
        expectedObject.put("deliveries", array);
        return expectedObject;
    }
}
