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
    CollectorConfig config = CollectorConfigParser.configJsonToObject();
    int count = 0;
    List<Achievement> aList = new ArrayList<>();
    List<UserAchievement> uaList;
    public Achievement generateAchievement(String name, String description, int value, final String command, float upper_bound){
        Achievement a1 = new Achievement();
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
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return aList;
    }
}
