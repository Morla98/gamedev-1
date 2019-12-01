package com.unihannover.gamedev;

import com.unihannover.gamedev.models.Achievement;
import com.unihannover.gamedev.models.Collector;
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
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;

@Service
public class CollectorService {

    @Autowired
    CollectorConfig config;

    public void sendPostRequest(Achievement a){
        String result = "";
        String url = "http://devgame:8080/api/achievements";
        HttpPost post = new HttpPost(url);
        Achievement test = new Achievement();
        a.setId("10");
        a.setCollectorId("0");
        a.setName("Congratulations!");
        a.setDescription("the dummy Collector is almost done!");
        a.setValue(0.5f);
        try {
            // send a JSON data
            post.setHeader("Accept", "*/*");
            post.setHeader("Content-type", "application/json");
            post.setEntity(new StringEntity(a.toJSON().toString()));
            System.out.println(a.toJSON().toString());
            CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse response = httpClient.execute(post);
            result = EntityUtils.toString(response.getEntity());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("\n\n" + result + "\n\n");
    }
    public void sendPostRequest(Collector c){
        String result = "";
        // TODO: adjust url
        String url = "http://devgame:8080/api/achievements";
        HttpPost post = new HttpPost(url);
        try {
            // send a JSON data
            post.setHeader("Accept", "*/*");
            post.setHeader("Content-type", "application/json");
            post.setEntity(new StringEntity(c.toJSON().toString()));
            System.out.println(c.toJSON().toString());
            CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse response = httpClient.execute(post);
            result = EntityUtils.toString(response.getEntity());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("\n\n" + result + "\n\n");
    }
    @Bean
    CommandLineRunner initMetricDatabase(MetricRepository repository) {
        return args -> {
            System.out.println("Preloading " + repository.save(new Metric(666)));
        };
    }

    @Bean
    public void initCollector(){
        Collector me = new Collector();
        me.setName("GeneralCollector");
        me.setId("-1");
        me.setToken("");
        // sendPostRequest(me);
        System.out.println("Init Collector");
    }
}
