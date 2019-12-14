package com.unihannover.gamedev.restcontroller;

import com.unihannover.gamedev.models.Achievement;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
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
    // URL IS: http://JiraCollector:8080/update
    @RequestMapping(value="/update", method = RequestMethod.POST)
    public void update(@RequestBody JSONObject data) {
        System.out.println(data);
    }

}
