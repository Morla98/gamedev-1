package com.unihannover.gamedev.services;

import com.unihannover.gamedev.CollectorConfig;
import com.unihannover.gamedev.CollectorConfigParser;
import com.unihannover.gamedev.models.*;
import com.unihannover.gamedev.repositories.MetricRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Float.parseFloat;

@Service
public class AchievementGenerator {
    @Autowired
    MetricRepository repository;
    int count = 0;
    List<Achievement> aList = new ArrayList<>();
    List<UserAchievement> uaList;
    public Achievement generateAchievement(String name, String description, int value, final String command, float upper_bound){
        Achievement a1 = new Achievement();
        CollectorConfig config = CollectorConfigParser.configJsonToObject();
        a1.setCollectorId(config.getCollectorId());
        a1.setId("c" + config.getCollectorId() + count);
        count++;
        a1.setName(name);
        a1.setDescription(description);
        a1.setValue(value);
        a1.setRepository(repository);
        try{
            Method method1 = Metric.class.getMethod(command);
            AchievementLogic logic = new AchievementLogic() {
                @Override
                public float complied(String useremail) {
                    Metric m = repository.findByUseremail(useremail).get(0);
                    try{
                    return ((int)(method1.invoke(m)) > upper_bound - 1) ? 100 : ((int)(method1.invoke(m)) / (upper_bound) * 100);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    return 0;
                }
            };
            a1.setLogic(logic);
        }catch (Exception e){
            e.printStackTrace();
        }
        return a1;
    }
    public List<Achievement> initAchievements(){

        CollectorConfig config = CollectorConfigParser.configJsonToObject();
        AchievementLogic logic;
        JSONParser parser = new JSONParser();
        try {
            Reader reader = new FileReader("config/achievements/config.json");
            JSONArray jsonArray = (JSONArray) parser.parse(reader);
            for(Object obj : jsonArray){
                JSONObject a = (JSONObject) obj;
                aList.add(generateAchievement((String) a.get("name"), (String) a.get("description"), ((Long) a.get("value")).intValue(), (String) a.get("command"), parseFloat(((String) a.get("upperbound")))));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*
        Achievement atest = generateAchievement("TestGeneretedAchieveemnt", "TestGeneretedAch", 10, "getNumberOfCommits", 200);
        aList.add(atest);
        Achievement a1 = generateAchievement("Hello Git", "Push for the First Time", 10, "getNumberOfCommits", 1);
        Achievement a2 = generateAchievement("Experienced Commiter", "Push 100 times", 50, "getNumberOfCommits", 100);
        Achievement a3 = generateAchievement("push, Eat, Repeat", "Push 100 times between 12am and 1pm", 50, "getDinnerCommits", 100);
        aList.add(a1); aList.add(a2); aList.add(a3);
        Achievement a4 = generateAchievement("Correct Commit Messages", "100 commits with correct message format", 5, "getNumberOfCorrectCommitMessages", 100);
        aList.add(a4);
        Achievement a5 = generateAchievement("Creator of Files", "Create 100 Files", 20, "getNumberOfNewFiles", 100);
        aList.add(a5);
        Achievement a6 = generateAchievement("Get some Sleep", "make 50 commits between 0am and 4am", 0, "getNightCommits", 50);
        aList.add(a6);
        */
        return aList;
    }
}
