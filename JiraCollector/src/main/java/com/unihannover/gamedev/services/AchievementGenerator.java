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
        a1.setName("Hello Jira");
        a1.setDescription("Create your first Ticket");
        a1.setValue(1);
        a1.setRepository(repository);
        logic = new AchievementLogic() {
            @Override
            public float complied(String useremail) {
                Metric m = repository.findByUseremail(useremail).get(0); //TODO handle size != 1
                float result = 0f;
                if(m.getIssue_created() > 0){
                    result = 100f;
                }
                return result;
            }
        };
        a1.setLogic(logic);
        aList.add(a1);

        Achievement a2 = new Achievement();
        a2.setCollectorId(config.getCollectorId());
        a2.setId("c" + config.getCollectorId() + "2");
        a2.setName("Step by Step");
        a2.setDescription("Move your first Ticket");
        a2.setValue(1);
        a2.setRepository(repository);
        logic = new AchievementLogic() {
            @Override
            public float complied(String useremail) {
                Metric m = repository.findByUseremail(useremail).get(0);
                float result = 0f;
                if(m.getIssue_updated() > 0){
                    result = 100f;
                }
                return result;
            }
        };
        a2.setLogic(logic);
        aList.add(a2);

        return aList;

    }
}
