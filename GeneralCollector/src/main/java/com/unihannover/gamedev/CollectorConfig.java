package com.unihannover.gamedev;

import org.springframework.context.annotation.Configuration;

@Configuration
public class CollectorConfig {
    private String collectorId;
    private String token;

    public String getCollectorId() {
        return collectorId;
    }

    public void setCollectorId(String collectorId) {
        this.collectorId = collectorId;
    }

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
