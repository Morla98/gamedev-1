package com.unihannover.gamedev.restcontroller;

import com.unihannover.gamedev.models.Achievement;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

@RestController
public class CollectorController {


    /**
     * Initialize the Collector on the main application
     */
    public void init(){
    }

    /**
     * gets called on hook from application
     */
    @CrossOrigin(origins = "http://localhost:9082")
    @RequestMapping(value="/update", method = RequestMethod.POST)
    public void update(@RequestBody Json data){
    }


}
