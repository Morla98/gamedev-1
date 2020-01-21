package com.unihannover.gamedev.component.achievements;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.unihannover.gamedev.models.Configuration;

class AchievementConfigurationTest {
	
	@Test
	void testApplyConfiguration() {
		
		Configuration configuration = new Configuration();
		configuration.setCollectorId("JiraCollector");
		
		//testing EpicKiller configuration
		EpicKiller epicKiller =new EpicKiller();
		epicKiller.applyConfiguration(configuration);
		//Achievement Id test 
		assertEquals("cJiraCollector7", epicKiller.getId());
		assertNotEquals("cJiraCollector1", epicKiller.getId());
		assertNotEquals("cjiraCollector7", epicKiller.getId());
		//Description test 
		assertEquals("Complete 10 Epics", epicKiller.getDescription());
		assertNotEquals("complete 10 Epics", epicKiller.getDescription());
		//achievement values test 
		assertEquals(10f, epicKiller.getValue());
		assertNotEquals(10.05, epicKiller.getValue(), 0.01);
		
		
		//testing MyJob configuration
		MyJob myjob = new MyJob();
		myjob.applyConfiguration(configuration);
		//Achievement Id test 
		assertEquals("cJiraCollector2", myjob.getId());
		assertNotEquals("cJiraCollector1", myjob.getId());
		assertNotEquals("cjiraCollector7", myjob.getId());
		//Description test 
		assertEquals("Assign 10 different issues to yourself", myjob.getDescription());
		assertNotEquals("assign 10 different issues to yourself", myjob.getDescription());
		//achievement values test 
		assertEquals(10f, myjob.getValue());
		assertNotEquals(10.05, myjob.getValue(), 0.01);
		
		
		//testing notMyJob configuration
		NotMyJob notMYJob = new NotMyJob();
		notMYJob.applyConfiguration(configuration);
		//Achievement Id test 
		assertEquals("cJiraCollector3", notMYJob.getId());
		assertNotEquals("cJiraCollector1", notMYJob.getId());
		assertNotEquals("cjiraCollector3", notMYJob.getId());
		//Description test 
		assertEquals("Assign 10 different issues from yourself to someone else", notMYJob.getDescription());
		assertNotEquals("assign 10 different issues from yourself to someone else", notMYJob.getDescription());
		//achievement values test 
		assertEquals(10f, notMYJob.getValue());
		assertNotEquals(10.05, notMYJob.getValue(), 0.01);
		
		//testing storyTeller COnfiguration
		StoryTeller storyTeller = new StoryTeller();
		storyTeller.applyConfiguration(configuration);
		//Achievement Id test 
		assertEquals("cJiraCollector8", storyTeller.getId());
		assertNotEquals("cJiraCollector1", storyTeller.getId());
		assertNotEquals("cjiraCollector8", storyTeller.getId());
		//Description test 
		assertEquals("Create 10 User Stories", storyTeller.getDescription());
		assertNotEquals("Create 10 User stories", storyTeller.getDescription());
		//achievement values test 
		assertEquals(10f, storyTeller.getValue());
		assertNotEquals(10.05, storyTeller.getValue(), 0.01);
		
		
		//testing theCleaner Configuration
		TheCleaner theCleaner = new TheCleaner();
		theCleaner.applyConfiguration(configuration);
		//Achievement Id test 
		assertEquals("cJiraCollector4", theCleaner.getId());
		assertNotEquals("cJiraCollector1", theCleaner.getId());
		assertNotEquals("cjiraCollector4", theCleaner.getId());
		//Description test 
		assertEquals("Delete 5 issues", theCleaner.getDescription());
		assertNotEquals("delete 5 issues..", theCleaner.getDescription());
		//achievement values test 
		assertEquals(5f, theCleaner.getValue());
		assertNotEquals(5.05, theCleaner.getValue(), 0.01);
		
		
		//testing theCommunicative configuration
		TheCommunicative theCommunicative = new TheCommunicative();
		theCommunicative.applyConfiguration(configuration);
		//Achievement Id test 
		assertEquals("cJiraCollector6", theCommunicative.getId());
		assertNotEquals("cJiraCollector1", theCommunicative.getId());
		assertNotEquals("cjiraCollector6", theCommunicative.getId());
		//Description test 
		assertEquals("Comment on 50 different issues", theCommunicative.getDescription());
		assertNotEquals("Comment on 50 Different issues", theCommunicative.getDescription());
		//achievement values test 
		assertEquals(50f, theCommunicative.getValue());
		assertNotEquals(50.05, theCommunicative.getValue(), 0.01);
		
		
		//testing the Workaholic configuration
		Workaholic workaholic = new Workaholic();
		workaholic.applyConfiguration(configuration);
		//Achievement Id test 
		assertEquals("cJiraCollector10", workaholic.getId());
		assertNotEquals("cJiraCollector1", workaholic.getId());
		assertNotEquals("cjiraCollector10", workaholic.getId());
		//Description test 
		assertEquals("Complete 999 issues", workaholic.getDescription());
		assertNotEquals("Complete 99 issues", workaholic.getDescription());
		//achievement values test 
		assertEquals(10f, workaholic.getValue());
		assertNotEquals(10.02, workaholic.getValue(), 0.01);
		
		TheReporter theReporter = new TheReporter();
		theReporter.applyConfiguration(configuration);
		//Achievement Id test 
		assertEquals("cJiraCollector5", theReporter.getId());
		assertNotEquals("cJiraCollector1", theReporter.getId());
		assertNotEquals("cjiraCollector5", theReporter.getId());
		//Description test 
		assertEquals("Create 50 issues", theReporter.getDescription());
		assertNotEquals("create 50 issues", theReporter.getDescription());
		//achievement values test 
		assertEquals(50f, theReporter.getValue());
		assertNotEquals(50.02, theReporter.getValue(), 0.01);
		
	}
	
}
