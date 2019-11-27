package com.unihannover.gamedev.component;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

@Component
public class AppStartupRunner implements ApplicationRunner {
    private static String requestUrl = "http://localhost:8082/api";

    private static String sendPostRequest(String payload) throws IOException {
        String command =  "curl -X POST \"http://localhost:8082/api/achievements\" -H  \"accept: /\" -H  \"Content-Type: application/json\" -d \"{  \"collectorId\": 0,  \"description\": \"test\",  \"name\": \"test2\",  \"value\": 0}\"";
        ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
        processBuilder.directory(new File("/home/"));
        Process process = processBuilder.start();
        int exitCode = process.exitValue();
        System.out.printf("\n\n\n");
        System.out.println(exitCode + "\n\n\n");
        return "";
    }

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

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // TODO: Init Achievements and send them to server
        test();

    }
}
