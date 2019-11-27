package com.unihannover.gamedev.restcontroller;

import com.unihannover.gamedev.models.Achievement;
import com.unihannover.gamedev.models.Collector;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
public class CollectorController {
    private static String requestUrl = "localhost:8082";
    private Collector collector;


    /**
     * Initialize the Collector on the main application
     */
    @PostConstruct
    public void init(){
        createCollector();
        createMetricDB();
        createAchievements();
        createUsers();
        // TODO: Transfer important Data into JSON Format
        String payload = "";
        sendPostRequest("/init", payload);
    }

    /**
     * Add Users in the users List
     */
    private void createUsers() {
        // TODO: implement
    }

    private void createCollector() {
        collector = new Collector(1, "Dummy");
    }

    private void createMetricDB(){
        // TODO: implement
    }

    /**
     * The Achievements will be defined here and will be added to the achievements list of the connector
     */
    /*
    private void createAchievements(){
        Achievement a1 = new Achievement(0,
                                         "Dummy Achievement",
                                         "This is an dummy Achievement",
                                         1,
                                         10,
                                         collector.getId());

        collector.getAchievements().add(a1);
        // ...
    }
    */
    private static String sendPostRequest(String api, String payload) {
        try {
            URL url = new URL(requestUrl+api);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
            writer.write(payload);
            writer.close();
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer jsonString = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                jsonString.append(line);
            }
            br.close();
            connection.disconnect();
            return jsonString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }


    /**
     * for User check each achievement with MetricDB and send updated values to main application
     */
    /*
    private void updateAchievements(User user){
        for(int id = 0; id < collector.getAchievements().size(); ++id){
            if(id == 0){
                Achievement a = collector.getAchievements().get(id);
                String payload = "";
                // make payload to JSON-String with data from Achievement a and MetricDB
                sendPostRequest("/update", payload);
            }
            if(id == 0){
                // ..
            }
            // ...
        }
    }
    */
    /**
     * gets called on hook from application
     * the data from the hook is stored in the MetricDB
     */
    @CrossOrigin(origins = "http://localhost:9082")
    @RequestMapping(value="/update", method = RequestMethod.GET)
    public void update(@RequestBody String data){
        System.out.println("Hello World!");
    }


}
