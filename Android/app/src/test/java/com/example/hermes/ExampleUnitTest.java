/*ckage com.example.hermes;

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

public class ExampleUnitTest {
    /*

    JsonManager jManager = new JsonManager();
    Delivery delivery1 = new Delivery();
        //delivery1.setCustomerID("Emrik") "2022-11-04", "12:45");

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
        String absolutePath = System.getProperty("user.dir") + "\\src\\main\\java\\com\\example\\hermes\\data\\";
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

    private JSONObject createExpectedObject(){
        JSONObject expectedObject = new JSONObject();
        JSONObject deliveryDetails = new JSONObject();
        JSONObject delivery = new JSONObject();
        JSONArray array = new JSONArray();

        deliveryDetails.put("date", "2022-11-04");
        deliveryDetails.put("customerID", "Emrik");
        deliveryDetails.put("time", "12:45");
        delivery.put("delivery", deliveryDetails);
        array.add(delivery);
        expectedObject.put("deliveries", array);
        return expectedObject;
    }
    
     */
}
