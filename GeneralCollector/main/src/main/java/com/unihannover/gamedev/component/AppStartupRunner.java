package com.unihannover.gamedev.component;

import com.unihannover.gamedev.models.Metric;
import com.unihannover.gamedev.repositories.MetricRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

@Component
public class AppStartupRunner implements ApplicationRunner {
    //private static String requestUrl = "http://localhost:8082/api";




    private String sendPostRequest() {
        /*HttpURLConnection con = null;
        URL url;
        try {
            url = new URL("http://127.0.0.1:8082/api/achievements");
            try {
                con = (HttpURLConnection)url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json; utf-8");
                con.setRequestProperty("Accept", "application/json");
                con.setDoOutput(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
        }
        catch (Exception e){
            e.printStackTrace();
        }



        String jsonInputString = "{" +
                "  \"collectorId\": 0," +
                "  \"description\": \"TEST\"," +
                "  \"name\": \"TEST\"," +
                "  \"value\": 0" +
                "}";
        try(OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }
        catch (Exception e){
            e.printStackTrace();
        }*/

        Runtime runtime = Runtime.getRuntime();

        try {
            Process process = runtime.exec("curl -X POST \"http://localhost:8082/api/achievements\" -H \"accept: */*\" -H \"Content-Type: application/json\" -d \"{ \\\"collectorId\\\": 0, \\\"description\\\": \\\"TEST\\\", \\\"name\\\": \\\"TEST\\\", \\\"value\\\": 0}\"");
            int resultCode = process.waitFor();

            if (resultCode == 0) {
                System.out.println("ofisduhiuosdgfghfooijgd");
                // all is good
            }
            else
            {
                System.out.println(resultCode + "I am a dissapointment");
            }
        } catch (Throwable cause) {
            // process cause
        }

        return "";
    }

    @Bean
    CommandLineRunner initMetricDatabase(MetricRepository repository) {
        return args -> {
            System.out.println("Preloading " + repository.save(new Metric(666)));
        };
    }

    /*
    private void test(){
        String payload = "{\n" +
                "  \"collectorId\": 0,\n" +
                "  \"description\": \"string\",\n" +
                "  \"name\": \"string\",\n" +
                "  \"value\": 0\n" +
                "}";
        try {
            sendPostRequest("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        // TODO: Init Achievements and send them to server
        System.out.println("Test finished");
        sendPostRequest();
    }
}
