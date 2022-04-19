package com.example.hermes;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class jsonManager {

    public void writeDelivery(Delivery delivery){

        JSONArray deliveries = readDelivery();
        JSONObject jsonDeliveryDetails = new JSONObject();

        jsonDeliveryDetails.put("customerID", delivery.customerID);
        jsonDeliveryDetails.put("time", delivery.time);
        jsonDeliveryDetails.put("date", delivery.date);

        JSONObject jsonDelivery = new JSONObject();
        jsonDelivery.put("delivery", jsonDeliveryDetails);

        deliveries.add(jsonDelivery);

        try (FileWriter file = new FileWriter("deliveries.Json")){
            file.write(deliveries.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JSONArray readDelivery(){

        JSONParser jsonParser = new JSONParser();
        JSONArray deliveryList = new JSONArray();

        try (FileReader reader = new FileReader("deliveries.Json")){
            Object object = jsonParser.parse(reader);
            deliveryList = (JSONArray) object;

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return deliveryList;
    }
}
