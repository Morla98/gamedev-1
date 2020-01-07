package com.unihannover.gamedev.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unihannover.gamedev.models.Configuration;
import com.unihannover.gamedev.models.Model;
import com.unihannover.gamedev.models.User;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Service
public class HttpService {

    CloseableHttpClient httpClient;

    @Autowired
    ConfigurationService configurationService;

    public List<User> getUsers() {
        Configuration config = configurationService.getConfig();

        httpClient = HttpClients.createDefault();
        CloseableHttpResponse response;
        HttpEntity result;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            HttpGet get = new HttpGet(config.getMainApiUrl() + "/users/all");
            get.setHeader("Accept", "*/*");
            get.setHeader("Content-type", "application/json");
            if (config.getToken() != null) {
                get.setHeader("X-Auth-Token", config.getToken());
            }
            response = httpClient.execute(get);
            String responsestring = EntityUtils.toString(response.getEntity(), "UTF-8");

            List<User> uList = objectMapper.readValue(responsestring, new TypeReference<List<User>>() {
            });
            return uList;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Returns true iff a user with the provided email address exists
     */
    public boolean isKnownUser(String userEmail) {
        List<User> users = this.getUsers();

        if (users == null) {
            return false;
        }

        for (User u : users) {
            if (userEmail.equals(u.getEmail())) {
                return true;
            }
        }
        return false;
    }

    public CloseableHttpResponse sendModel(Model m, String url) {
        List<Model> mList = new ArrayList<>();
        mList.add(m);
        return sendList(mList, url);
    }

    public CloseableHttpResponse sendList(List<Model> mList, String url) {
        Configuration config = configurationService.getConfig();

        HttpEntity result = null;
        String json = ListToJSON(mList);
        httpClient = HttpClients.createDefault();
        CloseableHttpResponse response;
        try {
            // send a JSON data
            HttpPost post = new HttpPost(config.getMainApiUrl() + url);
            post.setHeader("Accept", "*/*");
            post.setHeader("Content-type", "application/json");
            if (config.getToken() != null) {
                post.setHeader("X-Auth-Token", config.getToken());
            }
            post.setEntity(new StringEntity(json.toString()));
            // System.out.println(json.toString()); // Print json of to sending List
            response = httpClient.execute(post);
            result = response.getEntity();
            if (response.getStatusLine().getStatusCode() < 300) {
                return response;
                // updateWithToken(result);
            } else {
                System.out.println("Post failed: " + response.getStatusLine().getStatusCode());
                return null;
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public CloseableHttpResponse sendSingleModel(Model m, String url) {
        Configuration config = configurationService.getConfig();

        HttpEntity result = null;
        String json = m.toJSON();
        httpClient = HttpClients.createDefault();
        CloseableHttpResponse response;
        try {
            // send a JSON data
            HttpPost post = new HttpPost(config.getMainApiUrl() + url);
            post.setHeader("Accept", "*/*");
            post.setHeader("Content-type", "application/json");
            if (config.getToken() != null) {
                post.setHeader("X-Auth-Token", config.getToken());
            }
            post.setEntity(new StringEntity(json.toString()));
            // System.out.println(json.toString()); // Print json of to sending List
            response = httpClient.execute(post);
            result = response.getEntity();
            if (response.getStatusLine().getStatusCode() < 300) {
                return response;
            } else {
                System.out.println("Post failed: " + response.getStatusLine().getStatusCode());
                return null;
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String ListToJSON(List<Model> mList) {
        StringBuilder json = new StringBuilder();
        json.append("[");
        for (Model m : mList) {
            json.append(m.toJSON().toString() + ", ");
        }
        json.deleteCharAt(json.length() - 2);
        json.append("]");
        return json.toString();
    }
}
