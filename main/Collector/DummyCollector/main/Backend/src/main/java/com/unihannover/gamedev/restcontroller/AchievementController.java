package com.unihannover.gamedev.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unihannover.gamedev.models.Achievement;
import com.unihannover.gamedev.repositories.AchievementRepository;

@RestController
public class AchievementController {
	@Autowired
    private AchievementRepository repository;
	
	@CrossOrigin(origins = "http://localhost:9082")
	@RequestMapping(value="/update", method = RequestMethod.GET)
    public void jsonToCollector() {
        //call collector function
		System.out.println("Hello World!");
    }
	/*
	@CrossOrigin(origins = "http://localhost:9082")
	@RequestMapping(value="/achievements", method = RequestMethod.POST)
	public void addAchievement(@RequestBody Achievement a) {
		repository.save(a);
		System.out.println(a.toString());
	}*/
	
}