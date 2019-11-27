package com.unihannover.gamedev.restcontroller;

import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
public class CollectorController {


    /**
     * for User check each achievement with MetricDB and send updated values to main application
     */
    private void updateAchievements(){

    }


    /**
     * gets called on hook from application
     * the data from the hook is stored in the MetricDB
     */
    @RequestMapping(value="/update", method = RequestMethod.GET)
    public void update(@RequestBody String data){
        System.out.println("Hello World!");
    }


}
