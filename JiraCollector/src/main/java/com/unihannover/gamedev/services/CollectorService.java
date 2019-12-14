package com.unihannover.gamedev.services;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unihannover.gamedev.CollectorConfig;
import com.unihannover.gamedev.CollectorConfigParser;
import com.unihannover.gamedev.models.Achievement;
import com.unihannover.gamedev.models.Collector;
import com.unihannover.gamedev.models.Metric;
import com.unihannover.gamedev.models.Model;
import com.unihannover.gamedev.models.UserAchievement;
import com.unihannover.gamedev.repositories.MetricRepository;
import com.unihannover.gamedev.security.JwtTokenProvider;

@Service
public class CollectorService {
	@Value("${app.jwtSecret}")
	private String jwtSecret;

	/*
	@Autowired
	CollectorConfig config;
	*/

	@Autowired
	JwtTokenProvider tokenProvider;

	@Autowired
	HttpService httpService;

	@Bean
	CommandLineRunner initMetricDatabase(MetricRepository repository) {
		return args -> {
			System.out.println("Preloading " + repository.save(new Metric(666)));
		};
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
			me.setName("GeneralCollector");
			me.setId("general-collector");
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
		if (config.getCollectorId() != null) {
			initAchievements();
		}

	}

	private void initAchievements() {
		CollectorConfig config = CollectorConfigParser.configJsonToObject();
		List<Model> aList = new ArrayList<>();
		List<Model> uaList = new ArrayList<>();
		Achievement a1 = new Achievement();
		a1.setCollectorId(config.getCollectorId());
		a1.setId("c" + config.getCollectorId() + "-1");
		a1.setName("Test_Achievement1");
		a1.setDescription("This is a simple Test Achievement");
		a1.setValue(1);
		aList.add(a1);

		Achievement a2 = new Achievement();
		a2.setCollectorId(config.getCollectorId());
		a2.setId("c" + config.getCollectorId() + "-2");
		a2.setName("Test_Achievement2");
		a2.setDescription("This is a simple Test Achievement 2");
		a2.setValue(3);
		aList.add(a2);

		Achievement a3 = new Achievement();
		a3.setCollectorId(config.getCollectorId());
		a3.setId("c" + config.getCollectorId() + "-3");
		a3.setName("Test_Achievement3");
		a3.setDescription("This is a simple Test Achievement 3");
		a3.setValue(10);
		aList.add(a3);

		UserAchievement ua1 = new UserAchievement();
		ua1.setAchievementId("c" + config.getCollectorId() + "-1");
		ua1.setCollectorId(config.getCollectorId());
		ua1.setProgress(100f);
		ua1.setUserEmail("dev1@example.com");
		ua1.setLastUpdated(new Timestamp(System.currentTimeMillis()));
		uaList.add(ua1);

		UserAchievement ua2 = new UserAchievement();
		ua2.setAchievementId("c" + config.getCollectorId() + "-2");
		ua2.setCollectorId(config.getCollectorId());
		ua2.setProgress(80f);
		ua2.setUserEmail("dev1@example.com");
		ua2.setLastUpdated(new Timestamp(System.currentTimeMillis()));
		uaList.add(ua2);

		UserAchievement ua3 = new UserAchievement();
		ua3.setAchievementId("c" + config.getCollectorId() + "-3");
		ua3.setCollectorId(config.getCollectorId());
		ua3.setProgress(10f);
		ua3.setUserEmail("dev1@example.com");
		ua3.setLastUpdated(new Timestamp(System.currentTimeMillis()));
		uaList.add(ua3);

		httpService.sendList(aList, "http://devgame:8080/api/achievements");
		httpService.sendList(uaList, "http://devgame:8080/api/user-achievements");
		// http://JiraCollector:8080/update
		System.out.println("\nInit Achievements\n");
	}
}
