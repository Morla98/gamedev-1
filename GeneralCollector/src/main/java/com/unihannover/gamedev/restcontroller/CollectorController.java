package com.unihannover.gamedev.restcontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public void update(){
        System.out.println("Hello World!");
    }


}
