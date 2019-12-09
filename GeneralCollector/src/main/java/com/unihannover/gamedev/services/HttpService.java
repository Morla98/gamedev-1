package com.unihannover.gamedev.services;

import com.unihannover.gamedev.models.Model;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Service
public class HttpService {

    CloseableHttpClient httpClient;



    public String sendModel(Model m, String url){
       List<Model> mList = new ArrayList<>();
       mList.add(m);
       return sendList(mList, url);
    }

    public String sendList(List<Model> mList, String url){
        String result = "";
        String json = ListToJSON(mList);
        httpClient = HttpClients.createDefault();
        CloseableHttpResponse response;
        try {
            // send a JSON data
            HttpPost post = new HttpPost(url);
            post.setHeader("Accept", "*/*");
            post.setHeader("Content-type", "application/json");
            post.setEntity(new StringEntity(json.toString()));
            System.out.println(json.toString());
            response = httpClient.execute(post);
            result = EntityUtils.toString(response.getEntity());
            if (response.getStatusLine().getStatusCode() < 300 ) {
                return result;
                //updateWithToken(result);
            } else {
                System.out.println("Post failed: " + response.getStatusLine().getStatusCode());
                return "";
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

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
}
