package com.unihannover.gamedev.component;

import com.unihannover.gamedev.models.Metric;
import com.unihannover.gamedev.repositories.MetricRepository;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
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




    private String sendPostRequest2() {
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
            String url = "\"http://172.22.0.1:8082/api/achievements\"";
            StringBuilder json = new StringBuilder();
            json.append("\"{");
            json.append("\\\"collectorId\\\": 0,  ");
            json.append("\\\"description\\\":\\\"CollectorAchievement\\\",  ");
            json.append("\\\"name\\\":\\\"CollectorAchievement\\\",  ");
            json.append("\\\"value\\\": 0");
            json.append("}\"");

            String type = "\"Content-Type: application/json\"";
            String type2 = "\"accept: */*\"";
            String command = "curl -X POST " + url + " -H  " + type + " -H  " + type2 + " -d "+ json;
            System.out.println("\n " + command + "\n");
            Process process = runtime.exec(command);
            int resultCode = process.waitFor();
            System.out.println("\n " + command + "\n");
            if (resultCode == 0) {
                System.out.println("ofisduhiuosdgfghfooijgd");
                // all is good
            }
            else
            {
                System.out.println("\n " + command + "\n");
                System.out.println(resultCode + ": I am a dissapointment");
            }
        } catch (Throwable cause) {
            // process cause
            cause.printStackTrace();
        }

        return "";
    }
    private void sendPostRequest(){
        String result = "";
        String url = "http://devgame:8080/api/achievements";
        HttpPost post = new HttpPost(url);

        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append("\"collectorId\": 0,");
        json.append("\"description\": \"CollectorAchievement\",");
        json.append("\"name\": \"CollectorAchievement\",");
        json.append("\"value\": 0");
        json.append("}");

        try {
            // send a JSON data
            post.setHeader("Accept", "*/*");
            post.setHeader("Content-type", "application/json");
            post.setEntity(new StringEntity(json.toString()));

            CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse response = httpClient.execute(post);
            result = EntityUtils.toString(response.getEntity());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("\n\nRETRY\n\n");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            sendPostRequest();
        }
        System.out.println("\n\n" + result + "\n\n");
    }

    @Bean
    CommandLineRunner initMetricDatabase(MetricRepository repository) {
        return args -> {
            System.out.println("Preloading " + repository.save(new Metric(666)));
        };
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // TODO: Init Achievements and send them to server
        sendPostRequest();
        System.out.println("Test finished");
    }
}
