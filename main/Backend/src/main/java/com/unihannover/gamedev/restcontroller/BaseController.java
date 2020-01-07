package com.unihannover.gamedev.restcontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * An abstract class that represents a controller.
 */
@RequestMapping("/api")
@RestController
public abstract class BaseController {}