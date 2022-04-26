package com.example.hermes;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class JsonManager {

    public void writeDelivery(Delivery delivery, String filePath){
        String absolutePath = System.getProperty("user.dir") + "\\src\\main\\java\\com\\example\\hermes\\data\\";

        JSONObject jsonDeliveryDetails = new JSONObject();
        jsonDeliveryDetails.put("customerID", delivery.getCustomerID());
        jsonDeliveryDetails.put("time", delivery.getTime());
        jsonDeliveryDetails.put("date", delivery.getDate());

        JSONObject newDelivery = new JSONObject();
        newDelivery.put("delivery", jsonDeliveryDetails);

        JSONObject deliveries = readJsonObject(filePath);
        JSONArray array = (JSONArray) deliveries.get("deliveries");
        array.add(newDelivery);

        JSONObject deliveryList = new JSONObject();
        deliveryList.put("deliveries", deliveries);

        try (FileWriter file = new FileWriter(filePath)){
            file.write(deliveries.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JSONObject readJsonObject(String fileName){
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        String absoluteFilePath = System.getProperty("user.dir") + "\\src\\main\\java\\com\\example\\hermes\\data\\";

        try (FileReader reader = new FileReader(fileName)){
            jsonObject = (JSONObject) jsonParser.parse(reader);

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
