package com.unihannover.gamedev.services;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.unihannover.gamedev.models.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.util.EntityUtils;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unihannover.gamedev.CollectorConfig;
import com.unihannover.gamedev.CollectorConfigParser;
import com.unihannover.gamedev.repositories.MetricRepository;
import com.unihannover.gamedev.security.JwtTokenProvider;

@Service
public class CollectorService {
	@Value("${app.jwtSecret}")
	private String jwtSecret;
	private GitService gitservice;
	/*
	@Autowired
	CollectorConfig config;
	*/

	@Autowired
	JwtTokenProvider tokenProvider;

	@Autowired
	HttpService httpService;

	@Autowired
	AchievementGenerator achievementGenerator;

	List<Achievement> achievementList;

	public List<Achievement> getAchievementList() {
		return achievementList;
	}



	public void setAchievementList(List<Achievement> achievementList) {
		this.achievementList = achievementList;
	}

	@Bean
	public void initCollector() {
		CollectorConfig config = CollectorConfigParser.configJsonToObject();
		Collector me = new Collector();
		if (config != null) {
			me.setName(config.getName());
			me.setId(config.getCollectorId());
			me.setToken(config.getToken());
		} else {
			me.setName("GitCollector");
			me.setId("Git-collector");
			me.setToken("supersecretToken");
		}
		Timestamp t = new Timestamp(System.currentTimeMillis());
		me.setLastSeen(t);
		// config.setCollectorId(me.getId());

		HttpEntity result = httpService.sendSingleModel(me, "http://devgame:8080/api/collectors?secret=" + jwtSecret);

		ObjectMapper mapper = new ObjectMapper();
		Collector c = null;
		if (result != null) {
			try {
				// getting the JsonString
				String responseObject = EntityUtils.toString(result);
				System.out.println("response: " + responseObject);
				// Parsing the JsonString
				c = mapper.readValue(responseObject, Collector.class);
			} catch (org.apache.http.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (c != null) {
				System.out.println("Found in Response: " + c.getToken() + " " + c.getId());
				// updateWithToken(c.getToken());
				if (c.getToken() != null && tokenProvider.validateToken(c.getToken())) {
					config.setToken(c.getToken());
				}
				if (c.getId() != null) {
					config.setCollectorId(c.getId());
				}
				CollectorConfigParser.configCollectorToJson(config);
				System.out.println("\nInit Collector\n");
			}
		}
		// TODO: reported should be true if collector is already known by server so that he doesnt (re)send his Achievements
		boolean reported = false;
		initAchievements(reported);



		JSONParser parser = new JSONParser();

		try (Reader reader = new FileReader("config/collectorConfiguration/gitCredentials.json")) {
			JSONObject jsonObject = (JSONObject) parser.parse(reader);
			CloneCommand cloneCommand = Git.cloneRepository();
			String repoURL = (String) jsonObject.get("repoURL");
			String repoFile = "./repo";
			String adminName =  (String) jsonObject.get("adminName");
			String adminPass = (String) jsonObject.get("adminPass");
			cloneCommand.setURI( repoURL);
			cloneCommand.setDirectory(new File(repoFile));
			cloneCommand.setCredentialsProvider( new UsernamePasswordCredentialsProvider(adminName, adminPass ));
			try{
				cloneCommand.call();
			} catch (Exception e) {
				e.printStackTrace();
			}
			FileRepositoryBuilder repositoryBuilder = new FileRepositoryBuilder();
			try{
				Git git = Git.open(new File(repoFile + "/.git"));
				Repository repository = repositoryBuilder.setGitDir(new File(repoFile + "/.git")).readEnvironment().findGitDir().setMustExist(true).build();
				gitservice = new GitService(repository, git);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}



	}

	private void initAchievements(boolean reported) {
		CollectorConfig config = CollectorConfigParser.configJsonToObject();
		achievementList = achievementGenerator.initAchievements();
		List<User> uList = httpService.getUsers();
		List<UserAchievement> uaList = new ArrayList<>();


		// remake lists as model lists because httpService.sendList() can only take models...
		List<Model> aModelList = new ArrayList<>();
		List<Model> uaModelList = new ArrayList<>();
		for(Achievement achievement: achievementList){
			aModelList.add(achievement);
		}

		// Create UserAchievements for each User and for each Achievement
		UserAchievement userAchievement;
		for(User user: uList){
			for(Achievement achievement: achievementList){
				userAchievement = new UserAchievement();
				userAchievement.setAchievementId(achievement.getId());
				userAchievement.setCollectorId(config.getCollectorId());
				userAchievement.setUserEmail(user.getEmail());
				userAchievement.setProgress(0f);
				userAchievement.setLastUpdated(new Timestamp(System.currentTimeMillis()));
				uaList.add(userAchievement);
				uaModelList.add(userAchievement);
			}
		}
		// only if collector was not known he should send the initalize Achievementlist and UserAchievementList
		if(!reported){
			httpService.sendList(aModelList, "http://devgame:8080/api/achievements");
			httpService.sendList(uaModelList, "http://devgame:8080/api/user-achievements");
		}
		System.out.println("\nInit Achievements\n");

	}
}