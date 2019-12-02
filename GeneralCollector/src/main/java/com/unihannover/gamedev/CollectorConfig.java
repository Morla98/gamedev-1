package com.unihannover.gamedev;

import org.springframework.context.annotation.Configuration;

@Configuration
public class CollectorConfig {
    private String collectorId;

    public String getCollectorId() {
        return collectorId;
    }

    public void setCollectorId(String collectorId) {
        this.collectorId = collectorId;
    }
}
