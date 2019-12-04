package com.unihannover.gamedev.restcontroller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.unihannover.gamedev.models.Achievement;

class AchievementControllerTest {

	@Test
	void testAddAchievementList() {
		AchievementController achievementController = new AchievementController();
		Achievement[] achievementsArray = new Achievement[3];
		
		Achievement achievement1 = new Achievement();
		achievement1.setName("The journey Begins");
		achievement1.setDescription("First ever ticket, Welcome to Jira!");
		achievement1.setCollectorId("Jira Collector");
		
		Achievement achievement2 = new Achievement();
		achievement2.setName("Story teller");
		achievement2.setDescription("Nobody knows how you made them up so quick! 10 story created.");
		achievement2.setCollectorId("Jira Collector");
		
		Achievement achievement3 = new Achievement();
		achievement3.setName("The Guy is a keeper.");
		achievement3.setDescription("Who commits more than 10 times a day");
		achievement3.setCollectorId("Git Collector");
		
		achievementsArray[0] = achievement1;
		achievementsArray[1] = achievement2;
		achievementsArray[2] = achievement3;
		
		for(Achievement a : achievementsArray ) {
		System.out.println(a.getName());
		}
		//fail to save Achievements by repository.
		achievementController.addAchievementList(achievementsArray);
		
		List<Achievement> achievementList = achievementController.getAllAchievements();
		for(Achievement a : achievementList) {
			System.out.println(a.getName());
		}
	}

}
