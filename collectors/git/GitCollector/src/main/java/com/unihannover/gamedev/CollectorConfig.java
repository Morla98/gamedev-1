package com.unihannover.gamedev;

import org.springframework.context.annotation.Configuration;


@Configuration
public class CollectorConfig {
	private String name;
	private String collectorId;
    private String token;



    public CollectorConfig() {}
    public CollectorConfig(String name, String collectorId, String token) {
    	this.name = name;
    	this.collectorId = collectorId;
    	this.token = token;
    }



    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
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
