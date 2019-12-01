package com.unihannover.gamedev.component;

import com.unihannover.gamedev.CollectorService;
import com.unihannover.gamedev.models.Achievement;
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
    @Autowired
    CollectorService service;



    @Override
    public void run(ApplicationArguments args) throws Exception {
        // TODO: Init Achievements and send them to server
        service.sendPostRequest(new Achievement());
        System.out.println("Test finished");
    }
}
