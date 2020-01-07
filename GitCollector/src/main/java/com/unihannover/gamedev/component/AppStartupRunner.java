package com.unihannover.gamedev.component;

import com.unihannover.gamedev.CollectorConfig;
import com.unihannover.gamedev.services.CollectorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppStartupRunner implements ApplicationRunner {
    @Autowired
    CollectorService service;
    @Autowired
    CollectorConfig config;



    @Override
    public void run(ApplicationArguments args) throws Exception {
        // TODO: Init Achievements and send them to server

        System.out.println("Test finished");
    }
}
