package com.unihannover.gamedev.restcontroller;

import com.unihannover.gamedev.services.HookHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CollectorController {

    @Autowired
    HookHandlerService hookHandlerService;

    /**
     * WebHook endpoint for JIRA
     */
    @RequestMapping(value="/update", method = RequestMethod.POST)
    public void update(@RequestBody String data) {
        // Pass object to hook handler
        hookHandlerService.handle(data);
    }
}
