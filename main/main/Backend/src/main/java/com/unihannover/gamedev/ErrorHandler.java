package com.unihannover.gamedev;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorHandler implements ErrorController {

	private static final String PATH = "/error";

	// Redirecting to the index.html on error
    @RequestMapping(value = PATH)
    public String error() {
        return "forward:/index.html";
    }
    

    // Redirecting to the index.html on everything thats not a file
    @RequestMapping(value = "/**/{path:[^.]*}")       
    public String redirect() {
        // Forward to home page so that route is preserved.
        return "forward:/index.html";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }

}
