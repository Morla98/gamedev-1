package com.unihannover.gamedev.services;

import com.unihannover.gamedev.CollectorConfig;
import com.unihannover.gamedev.CollectorConfigParser;
import com.unihannover.gamedev.models.*;
import com.unihannover.gamedev.repositories.MetricRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Service
public class AchievementGenerator {
    @Autowired
    MetricRepository repository;
    int count = 0;
    List<Achievement> aList = new ArrayList<>();
    List<UserAchievement> uaList;
    public Achievement generateAchievement(String name, String Description, int value, final String command, int upper_bound){
        Achievement a1 = new Achievement();
        CollectorConfig config = CollectorConfigParser.configJsonToObject();
        a1.setCollectorId(config.getCollectorId());
        a1.setId("c" + config.getCollectorId() + count);
        count++;
        a1.setName(name);
        a1.setDescription(Description);
        a1.setValue(value);
        a1.setRepository(repository);
        try{
            Method method1 = Metric.class.getMethod(command);
            AchievementLogic logic = new AchievementLogic() {
                @Override
                public float complied(String useremail) {
                    Metric m = repository.findByUseremail(useremail).get(0);
                    try{
                    return ((int)(method1.invoke(m)) > upper_bound - 1) ? 100 : ((int)(method1.invoke(m)) / (1f * upper_bound) * 100);
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
        Achievement atest = generateAchievement("TestGeneretedAchieveemnt", "TestGeneretedAch", 10, "getNumberOfCommits", 200);
        aList.add(atest);
        Achievement a1 = generateAchievement("Hello Git", "Push for the First Time", 10, "getNumberOfCommits", 1);
        Achievement a2 = generateAchievement("Experienced Commiter", "Push 50 times", 50, "getNumberOfCommits", 50);
        Achievement a3 = generateAchievement("push, Eat, Repeat", "Push 100 times between 12am and 1pm", 50, "getDinnerCommits", 100);
        aList.add(a1); aList.add(a2); aList.add(a3);
        /*Achievement a1 = new Achievement();
        a1.setCollectorId(config.getCollectorId());
        a1.setId("c" + config.getCollectorId() + "1");
        a1.setName("Hello Git");
        a1.setDescription("Push for the first Time");
        a1.setValue(1);
        a1.setRepository(repository);
        logic = new AchievementLogic() {
            @Override
            public float complied(String useremail) {
                Metric m = repository.findByUseremail(useremail).get(0); //TODO handle size != 1
                return (m.getNumberOfCommits() > 0) ? 100 : 0;
            }
        };
        a1.setLogic(logic);
        aList.add(a1);



        Achievement a2 = new Achievement();
        a2.setCollectorId(config.getCollectorId());
        a2.setId("c" + config.getCollectorId() + "2");
        a2.setName("Experienced Committer");
        a2.setDescription("Commit 50 times");
        a2.setValue(1);
        a2.setRepository(repository);
        logic = new AchievementLogic() {
            @Override
            public float complied(String useremail) {
                Metric m = repository.findByUseremail(useremail).get(0); //TODO handle size != 1
                return (m.getNumberOfCommits() > 49) ? 100 : ((m.getNumberOfCommits() / 50f) * 100f);
            }
        };
        a2.setLogic(logic);
        aList.add(a2);

        Achievement a3 = new Achievement();
        a3.setCollectorId(config.getCollectorId());
        a3.setId("c" + config.getCollectorId() + "3");
        a3.setName("Commit, Push, Eat, Repeat");
        a3.setDescription("Push 100 times between 12am and 1pm");
        a3.setValue(10);
        a3.setRepository(repository);
        logic = new AchievementLogic() {
            @Override
            public float complied(String useremail) {
                Metric m = repository.findByUseremail(useremail).get(0);
                return (m.getDinnerCommits() > 99) ? 100 : ((m.getDinnerCommits() / 100f) * 100f);
            }
        };
        a3.setLogic(logic);
        aList.add(a3);*/
        return aList;

    }
}
