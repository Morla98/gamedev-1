package com.unihannover.gamedev;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.unihannover.gamedev.models.Achievement;
import com.unihannover.gamedev.models.Collector;
import com.unihannover.gamedev.models.Metric;
import com.unihannover.gamedev.models.Model;
import com.unihannover.gamedev.models.UserAchievement;
import com.unihannover.gamedev.repositories.MetricRepository;
import com.unihannover.gamedev.security.JwtTokenProvider;

@Service
public class CollectorService {
	@Value("${app.jwtSecret}")
	private String jwtSecret;
	
    @Autowired
    CollectorConfig config;
    
    @Autowired
    JwtTokenProvider tokenProvider;


    public String ListToJSON(List<Model> mList){
        StringBuilder json = new StringBuilder();
        json.append("[");
        for(Model m : mList){
            json.append(m.toJSON().toString() + ", ");
        }
        json.deleteCharAt(json.length()-2);
        json.append("]");
        return json.toString();
    }
    public void sendPostRequest(UserAchievement a){
        String result = "";
        String url = "http://devgame:8080/api/user-achievements";
        HttpPost post = new HttpPost(url);
        ArrayList<NameValuePair> postParameters;
        StringBuilder json = new StringBuilder();
        json.append(a.toJSON());
        try {
            // send a JSON data
            post.setHeader("Accept", "*/*");
            post.setHeader("Content-type", "application/json");
            post.setEntity(new StringEntity(json.toString()));
            postParameters = new ArrayList<NameValuePair>();
            postParameters.add(new BasicNameValuePair("secret", jwtSecret));
            //post.setEntity(new UrlEncodedFormEntity(postParameters, "UTF-8"));
            System.out.println(json.toString());
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
    public void sendPostRequest(List<Model> mList){
        String result = "";
        String url = "http://devgame:8080/api/achievements";
        HttpPost post = new HttpPost(url);

        String json = ListToJSON(mList);

        try {
            // send a JSON data
            post.setHeader("Accept", "*/*");
            post.setHeader("Content-type", "application/json");
            post.setEntity(new StringEntity(json.toString()));
            System.out.println(json.toString());
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
        String url = "http://devgame:8080/api/collectors?secret=" + jwtSecret;
        HttpPost post = new HttpPost(url);
        try {
            // send a JSON data
            post.setHeader("Accept", "*/*");
            post.setHeader("Content-type", "application/json");
            post.setEntity(new StringEntity(c.toJSON()));
            System.out.println(c.toJSON());
            CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse response = httpClient.execute(post);
            if (response.getStatusLine().getStatusCode() < 300 ) {
            	result = EntityUtils.toString(response.getEntity());
            	updateWithToken(result);
            } else {
            	System.out.println("Add Collectore Reponsecopde: " + response.getStatusLine().getStatusCode());
            }
            // TODO: Handle result later


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Bean
    CommandLineRunner initMetricDatabase(MetricRepository repository) {
        return args -> {
            System.out.println("Preloading " + repository.save(new Metric(666)));
        };
    }
    
    public void updateWithToken(String token) {
    	if (tokenProvider.validateToken(token)) {
    		String id = tokenProvider.getCollectorIdFromJWT(token);
    		System.out.println("Found id from Token: " + id + " " + token);
    		if (id != null) {
    			config.setCollectorId(id);    
    			config.setToken(token);
    		}
    	}
    }

    @Bean
    public void initCollector(){
        Collector me = new Collector();
        me.setName("GeneralCollector");
        me.setId("-1");
        me.setToken("supersecretToken");
        Timestamp t = new Timestamp(System.currentTimeMillis());
        me.setLastSeen(t);
        config.setCollectorId(me.getId());
        sendPostRequest(me);
        System.out.println("\nInit Collector\n");
        initAchievements();

    }

    private void initAchievements() {
        List<Model> aList = new ArrayList<>();
        Achievement a1 = new Achievement();
        a1.setCollectorId(config.getCollectorId());
        a1.setId("1");
        a1.setName("Test_Achievement1");
        a1.setDescription("This is a simple Test Achievement");
        a1.setValue(1);
        aList.add(a1);

        Achievement a2 = new Achievement();
        a2.setCollectorId(config.getCollectorId());
        a2.setId("2");
        a2.setName("Test_Achievement2");
        a2.setDescription("This is a simple Test Achievement 2");
        a2.setValue(3);
        aList.add(a2);

        Achievement a3 = new Achievement();
        a3.setCollectorId(config.getCollectorId());
        a3.setId("3");
        a3.setName("Test_Achievement3");
        a3.setDescription("This is a simple Test Achievement 3");
        a3.setValue(10);
        aList.add(a3);

        UserAchievement ua1 = new UserAchievement();
        ua1.setAchievementId("1");
        ua1.setCollectorId(config.getCollectorId());
        ua1.setProgress(100f);
        ua1.setUserEmail("user@example.com");
        ua1.setLastUpdated(new Timestamp(System.currentTimeMillis()));

        sendPostRequest(aList);
        sendPostRequest(ua1);
        System.out.println("\nInit Achievements\n");
    }
}
