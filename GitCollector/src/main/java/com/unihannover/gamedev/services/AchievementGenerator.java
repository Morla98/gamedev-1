package com.unihannover.gamedev.services;

import com.unihannover.gamedev.CollectorConfig;
import com.unihannover.gamedev.CollectorConfigParser;
import com.unihannover.gamedev.models.*;
import com.unihannover.gamedev.repositories.MetricRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AchievementGenerator {
    @Autowired
    MetricRepository repository;

    List<Achievement> aList = new ArrayList<>();
    List<UserAchievement> uaList;

    public List<Achievement> initAchievements(){

        CollectorConfig config = CollectorConfigParser.configJsonToObject();
        AchievementLogic logic;

        Achievement a1 = new Achievement();
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
        aList.add(a3);

        return aList;

    }
}
